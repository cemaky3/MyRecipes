package com.example.myapplication11.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntity::class], version = 1)
abstract class RecipeDB : RoomDatabase() {

    abstract fun Recipes(): LocalRecipeStorageDAO

    companion object {
        private var INSTANCE: RecipeDB? = null

        fun getInstance(context: Context): RecipeDB? {
            if (INSTANCE == null) {
                synchronized(RecipeDB::class) {
                    INSTANCE = Room.inMemoryDatabaseBuilder(
                        context.applicationContext,
                        RecipeDB::class.java
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
