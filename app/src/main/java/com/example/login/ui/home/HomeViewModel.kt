package com.example.login.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.login.di.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val session: Session
) : ViewModel() {
    var state by mutableStateOf(HomeState(activeAccount = false))
        private set

    fun verificar(){
        viewModelScope.launch {
            session.isUserLoggedIn().collect { isLoggedIn ->
                state = state.copy(activeAccount = isLoggedIn)
            }
        }
    }
}
