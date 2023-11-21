package com.jsom.creditcard.creditcard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jsom.creditcard.R

class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CardFragment.newInstance())
                .commitNow()
        }
    }
}