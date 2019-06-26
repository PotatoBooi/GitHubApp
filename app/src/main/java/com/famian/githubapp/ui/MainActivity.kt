package com.famian.githubapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.famian.githubapp.R
import kotlinx.android.synthetic.main.activity_main.*
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

        Navigation.findNavController(this, R.id.nav_host_fragment)
        //toolbar.setNavigationIcon(R.drawable.back)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
//    override fun onBackPressed() {
//        backPressed() ?: run {
//            super.onBackPressed()
//        }
//    }
//
//    fun backPressed(): Unit? {
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//        return (navHostFragment?.childFragmentManager?.fragments?.get(0) as? NavigationFragment)?.onPressBack()
//    }

}
//interface OnBackListener {
//    fun onNavigationResult(result: Bundle)
//}
//override fun onBackPressed() {
//    backPressed() ?: run {
//        super.onBackPressed()
//    }
//}
//
//fun backPressed(): Unit? {
//    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//    return (navHostFragment?.childFragmentManager?.fragments?.get(0) as? NavigationFragment)?.onPressBack()
//}

