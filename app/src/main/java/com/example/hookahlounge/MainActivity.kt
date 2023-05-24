package com.example.hookahlounge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.hookahlounge.ui.lounge.LoungeListScreen
import com.example.hookahlounge.ui.lounge.LoungeScreen
import com.example.hookahlounge.ui.session.SessionListScreen
import com.example.hookahlounge.ui.session.SessionNewScreen
import com.example.hookahlounge.ui.theme.HookahLoungeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HookahLoungeTheme {
                SessionNewScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HookahLoungeTheme {
        LoungeListScreen()
    }
}