package com.luckylittlesparrow.core_base_api.navigation

import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Navigation tool to perform transitions between different modules.
 * Should be initialized at start up in [Activity.onCreate]
 */
interface NavigationController {

    fun isBottomBarInitialised(): Boolean

    fun isLastInTab(id: Int): Boolean

    /**
     * Get child FragmentManager of current NavHostFragment
     */
    fun getCurrentFragmentManager(): FragmentManager

    /**
     * Get navigation controller associated with current host fragment.
     * should be called only after [updateState] or [switchTo], otherwise [IllegalStateException]
     * will be thrown
     */
    fun getNavController(): NavController

    /**
     * Initialize navigation holder with params, what will be used across all application,
     * should be called only once in the start activity.
     *
     * @param fragmentManager to manage fragment transaction between modules
     * @param containerId main fragment container of the activity layout, where each graph
     * will place it's host fragment
     */
    fun updateState(fragmentManager: FragmentManager, containerId: Int)

    /**
     * @param navController controller of the first host fragment, can be null, if you don't have specified
     * start graph in app, in that case navController will be created in the first call of [switchTo]
     */
    fun initNavController(navController: NavController?)

    /**
     * Switch to the new flow-graph
     *
     * @param navGraph new graph in the chain
     * @param destination custom destination, if our graph don't have default destination,
     * and can have several start screens, if you want to use home destination, don't use this param
     */
    fun switchTo(@NavigationRes navGraph: Int, @IdRes destination: Int = -1, params: Bundle? = null)

    /**
     * Switch to the previous flow-graph in the chain.
     */
    fun switchToLastGraphInBackStack()

    fun setupWithNavController(
        bottomNavigationView: BottomNavigationView,
        navGraphIds: List<Int>,
        fragmentManager: FragmentManager,
        containerId: Int,
        intent: Intent
    ): LiveData<NavController>

    fun onBackStackChangedListener(bottomNavigationView: BottomNavigationView)

    fun clear()

    fun checkBackStackForLastTab()
}