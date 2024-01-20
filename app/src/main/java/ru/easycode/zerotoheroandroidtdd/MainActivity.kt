package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private var state: State = State.Initial

    private lateinit var textView: TextView
    private lateinit var layout: LinearLayout
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById(R.id.rootLayout)
        textView = findViewById(R.id.titleTextView)
        button = findViewById(R.id.removeButton)

        button.setOnClickListener {
            state = State.Removed
            state.execute(layout, textView, button)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(STATE_KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        state = savedInstanceState.getSerializable(STATE_KEY) as State
        state.execute(layout, textView, button)
    }

    companion object {
        private const val STATE_KEY = "STATE_KEY"
    }
}

interface State : Serializable {
    fun execute(linearLayout: LinearLayout, textView: TextView, button: Button)

    object Initial : State {
        override fun execute(linearLayout: LinearLayout, textView: TextView, button: Button) = Unit
    }

    object Removed : State {
        override fun execute(linearLayout: LinearLayout, textView: TextView, button: Button) {
            linearLayout.removeView(textView)
            button.isEnabled = false
        }
    }
}