package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val TEXT_VIEW_STATE_KEY = "TEXT_VIEW_STATE_KEY"
    private lateinit var textView: TextView
    private lateinit var layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById(R.id.rootLayout)
        textView = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.removeButton)

        button.setOnClickListener {
            layout.removeView(textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val isTextViewRemoved = textView.parent == null
        outState.putBoolean(TEXT_VIEW_STATE_KEY, isTextViewRemoved)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val wasTextViewRemoved = savedInstanceState.getBoolean(TEXT_VIEW_STATE_KEY)
        if (wasTextViewRemoved) {
            layout.removeView(textView)
        }
    }
}