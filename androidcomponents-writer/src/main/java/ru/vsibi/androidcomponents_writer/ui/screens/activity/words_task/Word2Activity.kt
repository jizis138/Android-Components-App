/**
 * Created by Dmitry Popov on 14.07.2024.
 */
package ru.vsibi.androidcomponents_writer.ui.screens.activity.words_task

import android.content.Intent
import ru.vsibi.androidcomponents_writer.ui.screens.activity.base.BaseTaskActivity
import ru.vsibi.androidcomponents_writer.ui.screens.activity.numbers_task.Number1Activity

class Word2Activity : BaseTaskActivity() {

    override fun next() = Intent(this, Word3Activity::class.java)

    override fun otherTask(): Intent = Intent(this, Number1Activity::class.java)

}