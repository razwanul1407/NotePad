package com.example.cryptopad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class EnterActivity : AppCompatActivity() {


    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)


        realm = Realm.getDefaultInstance()

        val notes = realm.where<Note>().findAll()
        val recyclerView = noteList
        val adapter = NoteAdapter(this, notes) { note ->
            startActivity<MainActivity>("input_password_Edit" to note.id)
        }

        recyclerView.adapter = adapter


        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        layout.stackFromEnd = true
        recyclerView.layoutManager = layout


        fab.setOnClickListener {
            startActivity<MainActivity>()
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}
