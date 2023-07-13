package com.example.hookahlounge.presentation.authorization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.presentation.authorization.viewmodel.LoginEvent
import com.example.hookahlounge.presentation.authorization.viewmodel.LoginUiState
import com.example.hookahlounge.presentation.authorization.viewmodel.LoginViewModel
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahSelectBox
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahTextField

@Composable
fun UserLoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    toLounge: (Long) -> (Unit),
) {

    LaunchedEffect(true) {
        viewModel.loadData()
    }

    val state = viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.value.isAuthorized) {
            WorkplaceScreen(
                state = state.value,
                toLounge = toLounge,
                onEvent = { event ->
                    viewModel.onEvent(event)
                }
            )
        } else {

            HookahTextField(
                value = state.value.user.login,
                label = "Email",
                width = 0.8f,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredEmail(it))
                }
            )
            HookahTextField(
                value = state.value.user.password,
                label = "Password",
                width = 0.8f,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                }
            )
            ElevatedButton(onClick = { viewModel.authorize() }) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
private fun WorkplaceScreen(
    state: LoginUiState,
    onEvent: (LoginEvent) -> (Unit),
    toLounge: (Long) -> (Unit),
) {
    val lounge = state.lounges.findLast { state.user.currentWorkplace == it.id } ?: Lounge()
    Box(modifier = Modifier.fillMaxWidth()) {
        HookahTextField(
            value = lounge.name,
            label = "Workplace",
            onValueChange = {},
            readOnly = true
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            HookahSelectBox(
                options = state.lounges,
                currentValue = lounge,
                getter = { it.name },
                onEvent = { onEvent(LoginEvent.SelectedWorkplace(it)) }
            )
        }
    }
    ElevatedButton(onClick = { toLounge(lounge.id) }) {
        Text(text = "OK")
    }
}