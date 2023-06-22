package com.example.hookahlounge.presentation.menu


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hookahlounge.domain.model.LoungeMenu
import com.example.hookahlounge.domain.model.Menu
import com.example.hookahlounge.presentation.hookah_ui_elements.HeadlineMedium
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahLazyColumn
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahTextField
import com.example.hookahlounge.presentation.menu.viewmodel.list.LoungeMenuListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuListScreen(
    loungeId: Long?,
    viewModel: LoungeMenuListViewModel = hiltViewModel(),
    openBottomSheet: Boolean,
    bottomSheetAction: () -> (Unit),
    toLoungeMenu: (Long?, Long?) -> (Unit),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(true) {
        viewModel.loadData(loungeId)
    }

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (state.value.isLoading) {
            CircularProgressIndicator()
        } else {
            HookahLazyColumn(items = state.value.loungeMenuList) {
                LoungeMenuContent(
                    loungeMenu = it,
                    toLoungeMenu = toLoungeMenu
                )
            }
        }
    }

    if (openBottomSheet) {
        MenuBottomSheet(
            menuList = state.value.availableToAdd,
            bottomSheetState = bottomSheetState,
            onDismissRequest = bottomSheetAction,
            toLoungeMenu = toLoungeMenu
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuBottomSheet(
    menuList: List<Menu>,
    bottomSheetState: SheetState,
    onDismissRequest: () -> (Unit),
    toLoungeMenu: (Long?, Long?) -> (Unit),
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState,
    ) {

        var filter by remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HookahTextField(label = "Search", value = filter, onValueChange = { filter = it })
        }

        HookahLazyColumn(
            wrapContent = false,
            items = menuList.filter { menu ->
                filter.uppercase() in menu.name.uppercase()
            },
            footer = {
                MenuBottomSheetFooter(action = { toLoungeMenu(null, null) })
            }

        ) {
            MenuItemContent(
                menu = it,
                toLoungeMenu = toLoungeMenu)
        }


    }
}

@Composable
private fun MenuBottomSheetFooter(action: () -> (Unit)) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(
            modifier = Modifier
                .padding(4.dp),
            onClick = action
        ) {
            Column {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Toggle drawer",
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text("Add", style = MaterialTheme.typography.labelSmall)
            }
        }
        Spacer(Modifier.height(32.dp))
    }

}

@Composable
private fun MenuItemContent(menu: Menu, toLoungeMenu: (Long?, Long?) -> (Unit)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = menu.name, Modifier.fillMaxWidth(0.9f))
        IconButton(onClick = { toLoungeMenu(menu.id, null) }) {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoungeMenuContent(loungeMenu: LoungeMenu, toLoungeMenu: (Long?, Long?) -> (Unit)) {
    ElevatedCard(onClick = { }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                HeadlineMedium(text = loungeMenu.menu.name)
                HeadlineMedium(text = "Price: ${loungeMenu.price}")
            }

            IconButton(
                onClick = { toLoungeMenu(null, loungeMenu.id) }
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }

        }
    }
}

