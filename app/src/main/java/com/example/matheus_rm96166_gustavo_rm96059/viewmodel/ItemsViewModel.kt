package com.example.matheus_rm96166_gustavo_rm96059.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.matheus_rm96166_gustavo_rm96059.data.ItemDao
import com.example.matheus_rm96166_gustavo_rm96059.data.ItemDatabase
import com.example.matheus_rm96166_gustavo_rm96059.model.ItemModel

class ItemsViewModel(application: Application) : AndroidViewModel(application) {


    private val itemDao: ItemDao

    val itemsLiveData: LiveData<List<ItemModel>>

    init {

        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "items_database"
        ).build()

        itemDao = database.itemDao()


        itemsLiveData = itemDao.getAll()
    }
    fun addItem(item: String, item2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = ItemModel(name = item, desc = item2)
            itemDao.insert(newItem)
        }
    }

}