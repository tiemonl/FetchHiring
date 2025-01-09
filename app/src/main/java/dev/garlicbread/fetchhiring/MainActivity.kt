package dev.garlicbread.fetchhiring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.garlicbread.fetchhiring.ui.screens.ItemListView
import dev.garlicbread.fetchhiring.ui.theme.FetchHiringTheme
import dev.garlicbread.fetchhiring.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchHiringTheme {
                ItemListView(viewModel)
            }
        }
    }
}