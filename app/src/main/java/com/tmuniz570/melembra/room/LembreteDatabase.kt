package com.tmuniz570.melembra.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tmuniz570.melembra.model.Lembrete

@Database(entities = [Lembrete::class], version = 1)
abstract class LembreteDatabase : RoomDatabase() {
    abstract fun lembreteDao(): LembreteDao

    companion object {
        @Volatile
        private var INSTANCE: LembreteDatabase? = null

        fun getInstance(context: Context?): LembreteDatabase {
            synchronized(this) {
                var instance: LembreteDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context!!,
                        LembreteDatabase::class.java,
                        "lembrete_database"
                    ).allowMainThreadQueries().build()
                }
                return instance
            }
        }
    }
}