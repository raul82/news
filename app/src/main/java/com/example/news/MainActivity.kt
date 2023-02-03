package com.example.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.news.MainViewModel
import com.example.news.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModel()

    val router : Router by inject()
    val navigatorHolder : NavigatorHolder by inject()

    private val navigator: Navigator = object : AppNavigator(this, R.id.main_container) {
        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.MainFragment())))
        }
        viewModel.loadingStateLiveDate.observe(this) {

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        println("MainActivity onStart")
        super.onStart()
    }

    override fun onResume() {
        println("MainActivity onResume")
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        println("MainActivity onPause")
        super.onPause()
    }

    override fun onStop() {
        println("MainActivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        println("MainActivity onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        println("MainActivity  onSaveInstanceState")
        super.onSaveInstanceState(outState)

    }

/*
    class MainViewModelFactory(val sharedPreferences: MySharedPreferences) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(sharedPreferences, API.newInstance()) as T
            }

            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }*/

}