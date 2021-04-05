package com.example.challenge01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var normalAdapter: CustomAdapter
    private lateinit var importantAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        normalAdapter = CustomAdapter(mutableListOf())
        importantAdapter = CustomAdapter(mutableListOf())

        findViewById<RecyclerView>(R.id.rvNormal).adapter = normalAdapter
        findViewById<RecyclerView>(R.id.rvNormal).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rvImportant).adapter = importantAdapter
        findViewById<RecyclerView>(R.id.rvImportant).layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnAdd).setOnClickListener{
            val itemName = findViewById<EditText>(R.id.etText).text.toString()
            if(itemName.isNotEmpty()){
                val newItem = ItemType(itemName)
                normalAdapter.addToList(newItem)
                findViewById<EditText>(R.id.etText).text.clear()
            }
        }

        findViewById<Button>(R.id.btnRemove).setOnClickListener{
            normalAdapter.deleteCheckedItem()
        }

        findViewById<Button>(R.id.btnToImportant).setOnClickListener{
            val checkedItemList = normalAdapter.getCheckedItem()
            checkedItemList.forEach{
                val newItem = ItemType(it.item)
                importantAdapter.addToList(newItem)
                normalAdapter.deleteCheckedItem()
            }
        }

        findViewById<Button>(R.id.btnToNormal).setOnClickListener{
            val checkedItemList = importantAdapter.getCheckedItem()
            checkedItemList.forEach{
                val newItem = ItemType(it.item)
                normalAdapter.addToList(newItem)
                importantAdapter.deleteCheckedItem()
            }
        }
    }
}