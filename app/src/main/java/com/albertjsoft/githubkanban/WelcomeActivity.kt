package com.albertjsoft.githubkanban

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by albertj on 13/10/2018.
 */
class WelcomeActivity  : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {
        if (view.id == R.id.bt_sign_in) {
            // TODO: START LIST REPOSITORY ACTIVITY
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)
        findViewById<View>(R.id.bt_sign_in).setOnClickListener(this)
    }
}