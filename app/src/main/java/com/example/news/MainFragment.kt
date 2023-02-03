package com.example.news

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.news.MainViewModel
import com.example.news.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel :  MainViewModel   by viewModel()
    private val binding  by viewBinding (FragmentMainBinding::bind )

    private lateinit var adapter : ArticlesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("MainFragment onViewCreated" )


        adapter = ArticlesAdapter()

        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticles.adapter = adapter

        lifecycleScope.launchWhenStarted {
            mainViewModel.articlesFlow().collect{
                adapter.setItems(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("MainFragment onDestroyView" )
    }


    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        println("MainFragment onDestroyView" )
    }

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
     }
     override fun onStart (){
         super.onStart()
     }
     override fun onResume (){
         super.onResume()
         println("MainFragment onResume" )
     }
     override fun onPause (){
         super.onPause()
         println("MainFragment onPause" )
     }
     override fun onStop (){
         super.onStop()
     }
     override fun onDestroy (){
         super.onDestroy()
     }
     override fun onDetach (){
         super.onDetach()
     }
     override fun onSaveInstanceState(outState: Bundle) {}



    companion object {
        fun getNewInstance(): MainFragment {
            return MainFragment().apply {
                /*           arguments = Bundle().apply {
                putInt(EXTRA_NUMBER, number)
                putLong(EXTRA_TIME, System.currentTimeMillis())
            }*/
            }
        }
    }
}