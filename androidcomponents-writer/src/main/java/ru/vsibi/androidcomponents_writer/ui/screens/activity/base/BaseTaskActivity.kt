/**
 * Created by Dmitry Popov on 14.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.activity.base

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.vsibi.androidcomponents_writer.R

abstract class BaseTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_content)

        findViewById<TextView>(R.id.activity_name).text = this.javaClass.simpleName

        findViewById<Button>(R.id.next).setOnClickListener {
            next()?.let {
                startActivity(it)
            } ?: finishAffinity()
        }

        findViewById<Button>(R.id.other_task).setOnClickListener {
            startActivity(otherTask())
        }

        findViewById<Button>(R.id.finish).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.finish_task).setOnClickListener {
            finishAffinity()
        }
    }

    abstract fun next(): Intent?
    abstract fun otherTask(): Intent

}