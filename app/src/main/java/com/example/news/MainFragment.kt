package com.example.news

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.news.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("MainFragment onViewCreated" )
        binding.buttonFirst.setOnClickListener {
       
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("MainFragment onDestroyView" )

        _binding = null
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