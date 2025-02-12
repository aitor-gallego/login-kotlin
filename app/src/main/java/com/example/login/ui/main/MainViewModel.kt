package com.example.login.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.di.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val session: Session
) : ViewModel() {
    var state by mutableStateOf(MainState(activeAccount = false))
        private set

    init {
        verifier()
    }

    private fun verifier() {
        viewModelScope.launch {
            session.isUserLoggedIn().collect { isLoggedIn ->
                state = MainState(activeAccount = isLoggedIn)
            }
        }
    }
}