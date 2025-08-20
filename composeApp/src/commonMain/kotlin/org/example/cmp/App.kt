package org.example.cmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.cmp.di.appModule
import org.example.cmp.presentation.navigation.MealsNavigation
import org.example.cmp.presentation.theme.MealsTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        MealsTheme {
            MealsNavigation()
        }
    }
}