package com.ilosipov.hashgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.ilosipov.hashgenerator.databinding.FragmentSuccessBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Class SuccessFragment
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 26.12.2020
 * @version $Id$
 */

class SuccessFragment : Fragment() {

    companion object {
        private const val TAG = "SuccessFragment"
    }

    private lateinit var binding: FragmentSuccessBinding
    private val args: SuccessFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.i(TAG, "onCreateView: initialization SuccessFragment.")
        binding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()),
            R.layout.fragment_success, container, false)

        Log.i(TAG, "onCreateView: hash = ${args.hash}")
        binding.apply {
            hashTextView.text = args.hash
            btnCopyHash.setOnClickListener { onCopyClicked() }
        }
        return binding.root
    }

    private fun onCopyClicked() {
        lifecycleScope.launch {
            val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Encrypted Text", args.hash)
            clipboardManager.setPrimaryClip(clipData)
            applyAnimations()
        }
    }

    private suspend fun applyAnimations() {
        binding.include.apply {
            messageBackground.animate().translationY(100F).duration = 200L
            messageTextView.animate().translationY(100F).duration = 200L
        }

        delay(2000L)

        binding.include.apply {
            messageBackground.animate().translationY(-100F).duration = 400L
            messageTextView.animate().translationY(-100F).duration = 400L
        }
    }
}