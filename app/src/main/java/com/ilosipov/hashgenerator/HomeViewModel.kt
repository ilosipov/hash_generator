package com.ilosipov.hashgenerator

import androidx.lifecycle.ViewModel
import java.security.MessageDigest

/**
 * Class HomeViewModel
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 24.12.2020
 * @version $Id$
 */

class HomeViewModel : ViewModel() {

    fun getHash(plainText: String, algorithm: String) : String {
        val bytes = MessageDigest.getInstance(algorithm).digest(plainText.toByteArray())
        return toHex(bytes)
    }

    private fun toHex(byteArray: ByteArray) : String = byteArray.joinToString("") { "%02x".format(it) }
}