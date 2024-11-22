package com.example.matheus_rm96166_gustavo_rm96059.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.matheus_rm96166_gustavo_rm96059.model.ItemModel


@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}