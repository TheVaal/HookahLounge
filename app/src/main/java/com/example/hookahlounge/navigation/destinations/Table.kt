package com.example.hookahlounge.navigation.destinations

import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import com.example.hookahlounge.navigation.LoungeNavGraph
import com.example.hookahlounge.navigation.NonDismissableDialog
import com.example.hookahlounge.navigation.navigator.LoungeNavigator
import com.example.hookahlounge.presentation.table.TableDialog
import com.ramcosta.composedestinations.annotation.Destination

@LoungeNavGraph
@Destination(style = NonDismissableDialog::class)
@Composable
fun NavTableDialogScreen(id: Long?, loungeId: Long?, navigator: LoungeNavigator) {
    ElevatedCard{
        TableDialog(id, loungeId) { navigator.navigateUp() }
    }
}