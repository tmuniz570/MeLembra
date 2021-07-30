package com.tmuniz570.melembra.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LembreteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lembrete: LembreteEntity)

    @Delete
    suspend fun delete(item: LembreteEntity)

    @Delete
    fun deleteItem(item: LembreteEntity)

    @Query("SELECT * FROM lembretes ORDER BY data ASC")
    fun getAll() : LiveData<List<LembreteEntity>>

    @Query("DELETE FROM lembretes")
    fun clearDB()
}