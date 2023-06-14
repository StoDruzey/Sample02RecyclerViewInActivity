package com.example.sample02recyclerviewinactivity

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sample02recyclerviewinactivity.databinding.ActivityMainBinding
import com.example.sample02recyclerviewinactivity.databinding.ItemBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater, null,false)
        setContentView(binding.root)

        val listItems = mutableListOf<String>()
        val adapter = ItemAdapter { item ->
            Toast.makeText(this, item, Toast.LENGTH_SHORT).show()
        }

        with(binding) {
            button.setOnClickListener {
                listItems.add("Item ${listItems.size}")
                adapter.setData(listItems)
//                adapter.notifyDataSetChanged()

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = adapter
            }
        }
    }
}

class ItemAdapter(
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    private val list = mutableListOf<String>()

    fun setData(data: List<String>) {
        list.clear()
        list.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class ItemViewHolder(
    private val binding: ItemBinding,
    private val onItemClicked: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.itemString.text = item
        binding.root.setOnClickListener {
            onItemClicked(item)
        }
    }
}