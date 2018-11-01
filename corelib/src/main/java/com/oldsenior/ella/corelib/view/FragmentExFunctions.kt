package com.oldsenior.ella.corelib.view

import android.support.v4.app.Fragment


/**
 * Find a [NavController] given a [Fragment]
 *
 * Calling this on a Fragment that is not a [NavHostFragment] or within a [NavHostFragment]
 * will result in an [IllegalStateException]
 */
fun Fragment.findNavController(): NavController =
        NavHostFragment.findNavController(this)