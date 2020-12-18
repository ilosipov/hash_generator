package com.ilosipov.hashgenerator

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.ilosipov.hashgenerator.databinding.FragmentHomeBinding

/**
 * Class HomeFragment
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 18.12.2020
 * @version $Id$
 */

class HomeFragment : Fragment() {

    companion object {
        private const val TAG = "HomeFragment"
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: initialization HomeFragment.")
        binding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()),
            R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)

        val hasAlgorithms = resources.getStringArray(R.array.hash_algorithms)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, hasAlgorithms)

        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }
}