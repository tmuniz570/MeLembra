package com.tmuniz570.melembra.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LembreteEntity::class], version = 1, exportSchema = false)
abstract class LembreteDatabase : RoomDatabase() {

    abstract val lembreteDao: LembreteDao

    companion object {
        @Volatile
        private var INSTANCE: LembreteDatabase? = null

        fun getInstance(context: Context) : LembreteDatabase{
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,LembreteDatabase::class.java,"LembreteDB")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
