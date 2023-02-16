package com.example.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.news.databinding.FragmentMainBinding
import com.example.news.databinding.FragmentNewsDetailBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewsDetailFragment : Fragment() {

    private val binding  by viewBinding (FragmentNewsDetailBinding::bind )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}