package com.example.login.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.login.data.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: Account)

    @Delete
    suspend fun delete(account: Account)

    @Update
    suspend fun update(account: Account)

    @Query ("SELECT * FROM Accounts WHERE id=id")
    suspend fun getAccountById(id: Int):Account?

    @Query ("SELECT * FROM Accounts WHERE email=email")
    suspend fun getAccountByEmail(email: String):Account?

    @Query("SELECT * FROM Accounts ORDER BY name ASC")
    fun getAllAccounts(): Flow<List<Account>>
}
