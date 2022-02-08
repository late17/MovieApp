package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.activities.SingleMovieActivity

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.button_activity)

//        recyclerView = findViewById(R.id.recylerView)
//
//        val layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager

        val btn = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.num)
        btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SingleMovieActivity::class.java)
            intent.putExtra("movieId", Integer.parseInt(text.text.toString()))
            startActivity(intent)
        })
    }
}