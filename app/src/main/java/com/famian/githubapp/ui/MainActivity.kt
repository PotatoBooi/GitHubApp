package com.famian.githubapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.famian.githubapp.R
import kotlinx.android.synthetic.main.appbar.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by kodein()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
    }
}
