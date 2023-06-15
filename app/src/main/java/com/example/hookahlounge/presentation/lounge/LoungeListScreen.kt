package com.example.hookahlounge.presentation.lounge

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.hookahlounge.domain.model.Lounge
import com.example.hookahlounge.presentation.hookah_ui_elements.HeadlineLarge
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahLazyColumn
import com.example.hookahlounge.presentation.hookah_ui_elements.TitleMedium
import com.example.hookahlounge.presentation.lounge.viewmodel.LoungeListViewModel
import com.example.hookahlounge.ui.theme.HookahLoungeTheme

@Composable
fun LoungeListScreen(viewModel: LoungeListViewModel = hiltViewModel(), toLounge: (Long) -> Unit) {

    val lounges = viewModel.loungePagingFlow.collectAsLazyPagingItems()
    LoungeListScreen(lounges, toLounge)

}

@Composable
private fun LoungeListScreen(list: List<Lounge>, toLounge: (Long) -> (Unit)) {
    HookahLazyColumn(items = list) {
        LoungeContent(it, toLounge)
    }
}

@Composable
private fun LoungeListScreen(lounges: LazyPagingItems<Lounge>, toLounge: (Long) -> (Unit)) {
    val context = LocalContext.current
    LaunchedEffect(key1 = lounges.loadState) {
        if (lounges.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (lounges.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (lounges.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            HookahLazyColumn(items = lounges) {
                LoungeContent(it, toLounge)
            }
        }
        if (lounges.loadState.append == LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoungeContent(item: Lounge?, toLounge: (Long) -> Unit) {
    if (item == null) return
    ElevatedCard(onClick = { toLounge(item.id) }) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            HeadlineLarge(
                text = "#${item.id} ${item.name}",
                fontWeight = FontWeight(1000)
            )
            TitleMedium(
                text = item.address,
                fontWeight = FontWeight(500)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoungeListPreview() {
    HookahLoungeTheme {
        LoungeListScreen(
            list = listOf()
        ) {}
    }
}