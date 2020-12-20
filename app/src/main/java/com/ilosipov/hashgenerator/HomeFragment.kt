package com.ilosipov.hashgenerator

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ilosipov.hashgenerator.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        binding.btnGenerator.setOnClickListener {
            lifecycleScope.launch {
                applyAnimations()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSuccessFragment())
            }
        }

        return binding.root
    }

    private suspend fun applyAnimations() {
        binding.apply {
            titleHomeFragment.animate().alpha(0F).duration = 400L
            btnGenerator.animate().alpha(0F).duration = 400L
            textInputLayout.animate()
                .alpha(0F)
                .translationXBy(1200F)
                .duration = 400L
            editPersonName.animate()
                .alpha(0F)
                .translationXBy(-1200F)
                .duration = 400L
        }
        delay(300)

        binding.apply {
            successBackground.animate().apply {
                alpha(1F).duration = 600L
//                rotationBy(720F).duration = 600L
                scaleXBy(900F).duration = 800L
                scaleYBy(900F).duration = 800L
            }
        }
        delay(500)

        binding.successImageView.animate().alpha(1F).duration = 1000L
        delay(1500L)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }
}