package dev.garlicbread.fetchhiring.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.garlicbread.fetchhiring.model.Item
import dev.garlicbread.fetchhiring.viewmodel.ItemListUiState
import dev.garlicbread.fetchhiring.viewmodel.MainViewModel

@Composable
fun ItemListView(
    viewModel: MainViewModel
) {
    val hiringList by viewModel.hiringListState.collectAsState()
    when (hiringList) {
        ItemListUiState.Error -> {
            Error()
        }
        ItemListUiState.Loading -> {
            Loading()
        }
        is ItemListUiState.Success -> {
            SuccessfulListView((hiringList as ItemListUiState.Success).itemList)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Error() {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        BasicAlertDialog(
            onDismissRequest =  { openDialog.value = false }
        ) {
            Surface(
                modifier = Modifier.wrapContentWidth().wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Something went wrong."
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(
                        onClick = { openDialog.value = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
fun SuccessfulListView(
    hiringList: List<Item>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        items(hiringList) {
            ItemRow(it)
        }
    }
}

@Composable
fun ItemRow(item: Item) {
    Row(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ).background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "List ID: ${item.listId}",
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        Text(
            text = "Name: ${item.name.orEmpty()}",
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        Text(
            text = "ID: ${item.id}",
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}