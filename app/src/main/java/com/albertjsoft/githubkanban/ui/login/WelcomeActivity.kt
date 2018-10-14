package com.albertjsoft.githubkanban.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatEditText
import android.view.View
import com.albertjsoft.githubkanban.R
import com.albertjsoft.githubkanban.ui.main.MainActivity

/**
 * Created by albertj on 13/10/2018.
 */
class WelcomeActivity  : AppCompatActivity(), View.OnClickListener {

    private var mUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        findViewById<View>(R.id.bt_search).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.bt_search){
            mUsername = findViewById<AppCompatEditText>(R.id.et_username).text.toString()
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("username", mUsername)
            startActivity(i)
        }
    }
}