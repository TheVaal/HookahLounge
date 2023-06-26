package com.example.hookahlounge.presentation.tobacco.viewmodel.list

import androidx.lifecycle.viewModelScope
import com.example.hookahlounge.domain.usecase.HardnessUseCase
import com.example.hookahlounge.domain.usecase.LoungeTobaccoUseCase
import com.example.hookahlounge.domain.usecase.ManufacturerUseCase
import com.example.hookahlounge.domain.usecase.TobaccoUseCase
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.example.hookahlounge.domain.util.BaseViewModel
import com.example.hookahlounge.domain.util.HookahResponse
import com.example.hookahlounge.domain.util.onError
import com.example.hookahlounge.domain.util.onSuccess
import com.example.hookahlounge.presentation.tobacco.viewmodel.ManufacturersHardness
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TobaccoListViewModel @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    private val tobaccoUseCase: TobaccoUseCase,
    private val manufacturerUseCase: ManufacturerUseCase,
    private val hardnessUseCase: HardnessUseCase,
    private val loungeTobaccoUseCase: LoungeTobaccoUseCase,
) : BaseViewModel<LoungeTobaccoListUiState>() {
    override val initialState: LoungeTobaccoListUiState
        get() = LoungeTobaccoListUiState()

    fun loadData(loungeId: Long?) {
        if (loungeId == null) return
        state = state.copy(isLoading = true)
        viewModelScope.launch(dispatchers.io + job) {

            loadSecondaryData().collect {
                it.onSuccess { loadPrimaryData(loungeId) }
                    .onError { state = state.copy(isLoading = false) }
            }
        }
    }

    private suspend fun loadSecondaryData(): Flow<HookahResponse<ManufacturersHardness>> {
        return hardnessUseCase.loadHardness()
            .combine(manufacturerUseCase.loadManufacturers()) { hardnessResponse, manufacturerResponse ->
                if (hardnessResponse is HookahResponse.Success && manufacturerResponse is HookahResponse.Success) {
                    HookahResponse.Success(
                        ManufacturersHardness(
                            manufacturersList = manufacturerResponse.data,
                            hardnessList = hardnessResponse.data
                        )
                    )
                } else {
                    HookahResponse.Error(errorMessage = "Unable to retrieve data")
                }
            }
    }

    private suspend fun loadPrimaryData(loungeId: Long) {
        tobaccoUseCase.loadTobaccos().collect { tobacco ->
            tobacco.onSuccess { tobaccoData ->
                loungeTobaccoUseCase.loadTobaccos(loungeId)
                    .collect { loungeTobaccoResponse ->
                        loungeTobaccoResponse.onSuccess { loungeMenuList ->
                            state = state.copy(
                                isLoading = false,
                                loungeTobaccoList = loungeMenuList,
                                tobaccoList = tobaccoData
                            )
                        }.onError {
                            state = state.copy(
                                isLoading = false,
                                tobaccoList = tobaccoData
                            )
                        }
                    }
            }.onError {
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }
}

