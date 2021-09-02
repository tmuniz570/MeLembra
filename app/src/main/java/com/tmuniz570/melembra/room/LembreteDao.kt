package com.tmuniz570.melembra.room

import androidx.room.*
import com.tmuniz570.melembra.model.Lembrete

@Dao
interface LembreteDao {
    @Query("SELECT * FROM lembrete")
    fun getAll(): List<Lembrete>

    @Query("SELECT * FROM lembrete WHERE id = :x")
    fun getLembreteById(x: Int): Lembrete

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEdit(lembrete: Lembrete)

    @Delete
    fun delete(lembrete: Lembrete)
}