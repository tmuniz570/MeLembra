package com.tmuniz570.melembra.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lembretes")
data class LembreteEntity(
    @PrimaryKey var id: Int,
    val lembrete: String,
    val data: String,
    val hora: String,
    val repetir: Boolean
){

    constructor():this(id = 0, lembrete = "lembrete", data = "data", hora = "hora", repetir = false){

    }
}