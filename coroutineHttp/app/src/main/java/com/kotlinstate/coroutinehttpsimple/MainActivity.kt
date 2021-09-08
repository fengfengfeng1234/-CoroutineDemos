package com.kotlinstate.coroutinehttpsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlinstate.coroutinehttpsimple.ui.main.MainFragment

/**
 * 官网参考
 *  https://developer.android.com/topic/libraries/architecture/coroutines?hl=zh-cn#lifecyclescope
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}