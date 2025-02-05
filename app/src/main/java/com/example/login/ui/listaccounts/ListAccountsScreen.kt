package com.example.login.ui.listaccounts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.login.data.model.Account

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun ListAccountScreen(viewModel: ListAccountViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Lista de Cuentas") }, actions = {
            Button(onClick = {}) {
                Text("Volver")
            }
        })
    }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                }

                state.errorMessage != null -> {
                    Text(
                        text = state.errorMessage ?: "Error desconocido",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                else -> {
                    AccountList(accounts = state.accounts)
                }
            }
        }
    }
}

@Composable
fun AccountList(accounts: List<Account>) {
    LazyColumn {
        items(accounts) { account ->
            AccountItem(account)
        }
    }
}

@Composable
fun AccountItem(account: Account) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = account.username, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

