package com.example.news

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.news.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("FirstFragment onViewCreated" )
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("FirstFragment onDestroyView" )

        _binding = null
    }


    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        println("FirstFragment onDestroyView" )
    }

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
     }
     override fun onStart (){
         super.onStart()
     }
     override fun onResume (){
         super.onResume()
         println("FirstFragment onResume" )
     }
     override fun onPause (){
         super.onPause()
         println("FirstFragment onPause" )
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
}