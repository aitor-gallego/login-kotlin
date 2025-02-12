package com.example.login.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName = "Accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,

    @ColumnInfo(name = "email") val email: String,

    @ColumnInfo(name = "password") val password: String,

    @ColumnInfo(name = "name") val name: String? = null,

    @ColumnInfo(name = "surname") val surname: String? = null,

    @ColumnInfo(name = "display_name") val displayName: String? = null,

    @ColumnInfo(name = "birthdate") val birthdate: Date? = null,

    @ColumnInfo(name = "firebase_uid") val firebaseUid: String? = null,

    @ColumnInfo(name = "photo_url") val photoUrl: String? = null,

    @ColumnInfo(name = "created_at") val createdAt: Date? = null,

    @ColumnInfo(name = "updated_at") val updatedAt: Date? = null
)
