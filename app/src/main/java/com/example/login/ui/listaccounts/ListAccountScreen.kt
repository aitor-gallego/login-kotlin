package com.example.login.ui.listaccounts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.login.data.model.Account
import com.example.login.icons.filter
import com.example.login.icons.order
import com.example.login.ui.components.Actions
import com.example.login.ui.components.BaseTopAppBar
import com.example.login.ui.components.BaseTopAppBarState
import kotlinx.coroutines.launch

data class AccountListEvents(
    val openDrawer: () -> Unit,
    val goToCreation: () -> Unit,
    val onSort: () -> Unit,
    val goToDetail: (Int) -> Unit
)

@Composable
fun ListAccountScreen(
    viewModel: ListAccountViewModel,
    openDrawer: () -> Unit,
    goToCreation: () -> Unit,
    goToDetail: (Int) -> Unit,
) {
    LaunchedEffect(viewModel) {
        viewModel.loadAccounts()
    }

    val events = AccountListEvents(
        openDrawer = openDrawer,
        goToCreation = goToCreation,
        goToDetail = goToDetail,
        onSort = { viewModel.onSort() },
    )

    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var selectedAccount by remember { mutableStateOf<Account?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { BaseTopAppBar(appBarState(events, viewModel)) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                state.errorMessage != null -> Text(
                    text = state.errorMessage ?: "Error desconocido",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxSize()
                )
                state.accounts.isEmpty() -> NoDataScreen()
                else -> {
                    LazyColumn {
                        items(state.accounts) { account ->
                            AccountItem(
                                account = account,
                                onClick = {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Cuenta: ${account.email}")
                                    }
                                },
                                onLongClick = {
                                    selectedAccount = account
                                    showDeleteDialog = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showDeleteDialog && selectedAccount != null) {
        DeleteAccountDialog(
            account = selectedAccount!!,
            onConfirm = {
                viewModel.deleteAccount(selectedAccount!!)
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Cuenta eliminada: ${selectedAccount!!.email}")
                }
                showDeleteDialog = false
            },
            onDismiss = { showDeleteDialog = false }
        )
    }
}

@Composable
fun appBarState(events: AccountListEvents, viewModel: ListAccountViewModel): BaseTopAppBarState {
    return BaseTopAppBarState(
        title = "Listado de cuentas",
        icon = Icons.Filled.Menu,
        actions = listOf(
            Actions(
                name = "filter",
                icon = filter(),
                contentDescription = "Filtrar cuentas",
                onClick = {}
            ),
            Actions(
                name = "sort",
                icon = order(),
                contentDescription = "Ordernar cuentas",
                onClick = {events.onSort()}
            )
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountItem(account: Account, onClick: () -> Unit, onLongClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = account.email, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun DeleteAccountDialog(account: Account, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Eliminar cuenta") },
        text = { Text(text = "¿Está seguro de que desea eliminar la cuenta: ${account.email}?") },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text(text = "Aceptar") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(text = "Cancelar") }
        }
    )
}

@Composable
fun NoDataScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No hay cuentas",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}