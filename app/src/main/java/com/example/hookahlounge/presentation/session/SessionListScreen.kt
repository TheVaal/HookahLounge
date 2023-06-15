package com.example.hookahlounge.presentation.session

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.hookahlounge.R
import com.example.hookahlounge.domain.model.Session
import com.example.hookahlounge.ui.theme.HookahLoungeTheme
import com.example.hookahlounge.presentation.hookah_ui_elements.HeadlineLarge
import com.example.hookahlounge.presentation.hookah_ui_elements.HookahLazyColumn
import com.example.hookahlounge.presentation.hookah_ui_elements.TitleLarge
import com.example.hookahlounge.presentation.session.viewmodel.SessionListViewModel

@Composable
fun SessionListScreen(
    newSession: () -> Unit,
    viewModel: SessionListViewModel = hiltViewModel()
) {
    val sessions = viewModel.loungePagingFlow.collectAsLazyPagingItems()
    SessionListScreen(sessions)

}

@Composable
private fun SessionListScreen(list: List<Session>) {
    HookahLazyColumn(items = list) {
        SessionContent(it)
    }
}

@Composable
private fun SessionListScreen(sessions: LazyPagingItems<Session>) {
    val context = LocalContext.current
    LaunchedEffect(key1 = sessions.loadState) {
        if (sessions.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (sessions.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (sessions.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            HookahLazyColumn(items = sessions) {
                SessionContent(it)
            }
        }
        if (sessions.loadState.append == LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun SessionContent(item: Session) {
    TextButton(onClick = { /*TODO*/ }) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                contentDescription = null,
                painter = getSessionStatusIcon(item.status),
                modifier = Modifier.fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                HeadlineLarge(
                    text = item.owner.name,
                    fontWeight = FontWeight(1000)
                )
                TitleLarge(
                    text = item.owner.phone,
                    fontWeight = FontWeight(500)
                )
                TitleLarge(
                    text = item.bookingDate,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }
}

@Composable
private fun getSessionStatusIcon(status: String): Painter {
    return if (status == "O") {
        painterResource(id = R.drawable.ic_lock_24)
    } else {
        painterResource(id = R.drawable.ic_lock_open_24)
    }
}

@Preview(showBackground = true)
@Composable
fun LoungeListPreview() {
    HookahLoungeTheme {

    }
}