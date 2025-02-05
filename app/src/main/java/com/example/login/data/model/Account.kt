package com.example.login.data.model

import androidx.room.Entity

@Entity
data class Account(val username: String, val password: String)