package com.example.myapplication11.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LocalRecipeStorageDAO {
    @Query("SELECT * FROM recipes")
    fun addAll(): LiveData<List<RecipeEntity>>
    @Insert
    fun insert(recipe: RecipeEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(recipe: RecipeEntity)
    @Delete
    fun delete(recipe: RecipeEntity)
    @Update
    fun update(vararg recipe: RecipeEntity)
}