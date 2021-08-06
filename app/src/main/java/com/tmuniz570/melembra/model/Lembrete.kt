package com.tmuniz570.melembra.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Lembrete(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val lembrete: String,
    val data: String,
    val hora: String,
    val repetir: Boolean
) : Parcelable
