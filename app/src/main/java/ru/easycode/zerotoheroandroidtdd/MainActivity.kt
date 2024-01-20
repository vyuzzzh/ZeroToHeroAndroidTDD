package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val TEXT_VIEW_STATE_KEY = "TEXT_VIEW_STATE_KEY"
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.hideButton)

        button.setOnClickListener {
            textView.visibility = View.INVISIBLE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TEXT_VIEW_STATE_KEY, textView.visibility)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt(TEXT_VIEW_STATE_KEY).apply {
            textView.visibility = this
        }
    }
}