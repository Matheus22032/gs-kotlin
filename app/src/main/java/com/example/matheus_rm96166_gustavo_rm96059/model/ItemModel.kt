package com.example.matheus_rm96166_gustavo_rm96059.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val desc: String
)