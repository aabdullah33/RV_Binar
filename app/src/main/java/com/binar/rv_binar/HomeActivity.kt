package com.binar.rv_binar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.rv_binar.data.Note
import com.binar.rv_binar.data.NoteDatabase
import com.binar.rv_binar.databinding.ActivityHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    var mDb: NoteDatabase? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDb = NoteDatabase.getInstance(this)

        binding.rvContainer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        fetchData()

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        val uname = sharedPreferences.getString("uname", "defaultUname")

        binding.tes.text = "Hi $uname"
        binding.tes3.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.remove("login").apply()
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        binding.floatingActionButton.setOnClickListener{
            val objectStudent = Note(
                null,
                "Judul",
                "etEmailStudent.text.toString()"
            )

            GlobalScope.async {
                val result = mDb?.noteDao()?.insertNote(objectStudent)
                runOnUiThread {
                    if(result != 0.toLong() ){
                        //sukses
                        Toast.makeText(this@HomeActivity,"Sukses menambahkan ${objectStudent.judul}",
                            Toast.LENGTH_LONG).show()
                    }else{
                        //gagal
                        Toast.makeText(this@HomeActivity,"Gagal menambahkan ${objectStudent.judul}",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch {
            val listStudent = mDb?.noteDao()?.getAllNote()

            runOnUiThread{
                listStudent?.let {
                    val adapter = NoteAdapter(it)
                    binding.rvContainer.adapter = adapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NoteDatabase.destroyInstance()
    }
}