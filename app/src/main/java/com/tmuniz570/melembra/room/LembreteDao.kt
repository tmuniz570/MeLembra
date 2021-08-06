package com.tmuniz570.melembra.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tmuniz570.melembra.model.Lembrete

@Dao
interface LembreteDao {

    @Query("SELECT * FROM lembrete")
    fun getAll(): List<Lembrete>

    @Insert
    fun insert(filmesEntity: Lembrete)

    @Delete
    fun delete(filmesEntity: Lembrete)

}