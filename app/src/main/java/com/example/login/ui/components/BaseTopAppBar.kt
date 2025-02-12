package com.example.login.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(appBarState: BaseTopAppBarState) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = appBarState.title) },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = appBarState.icon, contentDescription = "Navigation Icon")
            }
        },
        actions = {
            val visibleActions = appBarState.actions.filter { it.isVisible }
            val hiddenActions = appBarState.actions.filter { !it.isVisible }

            visibleActions.forEach { action ->
                IconButton(onClick = action.onClick) {
                    Icon(imageVector = action.icon, contentDescription = action.contentDescription)
                }
            }

            if (hiddenActions.isNotEmpty()) {
                IconButton(onClick = { expanded = true }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Options")
                }

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    hiddenActions.forEach { action ->
                        DropdownMenuItem(
                            text = {
                                Row {
                                    Icon(imageVector = action.icon, contentDescription = action.contentDescription)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(action.name)
                                }
                            },
                            onClick = {
                                action.onClick()
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    )
}
