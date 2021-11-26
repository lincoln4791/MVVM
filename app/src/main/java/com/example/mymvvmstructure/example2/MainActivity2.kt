package com.example.mymvvmstructure.example2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.mymvvmstructure.example2.ui.login.LoginFragment


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mymvvmstructure.R.layout.activity_main2)

        val myf = LoginFragment()

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(com.example.mymvvmstructure.R.id.frameLayout, myf)
        transaction.commit()

    }
}