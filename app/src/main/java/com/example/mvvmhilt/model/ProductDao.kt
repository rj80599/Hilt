package com.example.mvvmhilt.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(list: List<Product>)

    @Query("Select * from Product")
    suspend fun getData():List<Product>

}