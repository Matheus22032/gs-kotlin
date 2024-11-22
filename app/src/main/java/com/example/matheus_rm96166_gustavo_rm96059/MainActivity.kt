package com.example.matheus_rm96166_gustavo_rm96059

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.example.matheus_rm96166_gustavo_rm96059.viewmodel.ItemsAdapter
import com.example.matheus_rm96166_gustavo_rm96059.viewmodel.ItemsViewModel
import com.example.matheus_rm96166_gustavo_rm96059.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Ecodicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val itemsAdapter = ItemsAdapter { item ->
        }

        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)
        val editText2 = findViewById<EditText>(R.id.editText2)

        button.setOnClickListener {
            // Se o campo de texto estiver vazio, exibe um erro e retorna.
            if (editText.text.isEmpty()) {
                editText.error = "Preencha um valor"
                return@setOnClickListener
            }

            if (editText2.text.isEmpty()) {
                editText2.error = "Preencha um valor"
                return@setOnClickListener
            }

            viewModel.addItem(editText.text.toString(),editText2.text.toString())
            editText.text.clear()
            editText2.text.clear()
        }


        val viewModelFactory = ItemsViewModelFactory(application)
        // Obtém uma instância do ViewModel.
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)


        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val filteredItems = viewModel.itemsLiveData.value?.filter {
                    it.name.contains(newText.orEmpty(), ignoreCase = true)
                } ?: listOf()

                itemsAdapter.updateItems(filteredItems)
                return true
            }
        })
    }
}