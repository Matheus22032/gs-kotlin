package com.example.matheus_rm96166_gustavo_rm96059.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.matheus_rm96166_gustavo_rm96059.model.ItemModel

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemModel")
    abstract fun getAll(): LiveData<List<ItemModel>>

    @Insert
    abstract fun insert(item: ItemModel)

}