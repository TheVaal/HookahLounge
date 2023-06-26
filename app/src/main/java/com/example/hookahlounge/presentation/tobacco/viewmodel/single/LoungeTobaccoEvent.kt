package com.example.hookahlounge.presentation.tobacco.viewmodel.single

import com.example.hookahlounge.domain.model.Hardness
import com.example.hookahlounge.domain.model.Manufacturer

sealed class LoungeTobaccoEvent {
    data class EnteredTaste(val value: String) : LoungeTobaccoEvent()
    data class EnteredPrice(val value: String) : LoungeTobaccoEvent()
    data class EnteredManufacturerName(val value: String) : LoungeTobaccoEvent()
    data class SelectedManufacturerName(val value: Manufacturer) : LoungeTobaccoEvent()
    data class SelectedHardness(val value: Hardness) : LoungeTobaccoEvent()
}