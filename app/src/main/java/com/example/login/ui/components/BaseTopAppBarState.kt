package com.example.login.ui.components

import androidx.compose.ui.graphics.vector.ImageVector

data class BaseTopAppBarState(
    val title: String,
    val icon: ImageVector,
    val actions: List<Actions>
)

data class Actions(
    val name: String,
    val icon: ImageVector,
    val contentDescription: String,
    val onClick: () -> Unit,
    val isVisible: Boolean = true
)