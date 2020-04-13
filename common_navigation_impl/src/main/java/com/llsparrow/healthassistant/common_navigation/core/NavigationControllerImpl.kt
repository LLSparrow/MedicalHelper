package com.llsparrow.healthassistant.common_navigation.core

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.core.util.set
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.llsparrow.healthassistant.common_navigation.R
import com.llsparrow.healthassistant.common_navigation_api.navigation.core.NavigationController
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationControllerImpl @Inject constructor() : NavigationController {
    override fun checkBackStackForLastTab() {
        if (fragmentManager.backStackEntryCount == 1 && fragmentManager.getBackStackEntryAt(0).name == firstFragmentTag) {
            fragmentManager.popBackStack()
        }
    }

    private lateinit var navController: NavController
    private lateinit var fragmentManager: FragmentManager
    private var currentTabTag: String? = null
    private var lastTagInTab: String = ""
    private var firstFragmentTag: String = ""
    private var firstFragmentGraphId = 0
    private var tabs = HashMap<String, LinkedList<String>>()
    private val graphIdToTagMap = SparseArray<String>()
    private var navBarInitialised = false
    private val selectedNavController = MutableLiveData<NavController>()

    @IdRes
    private var containerId: Int = -1

    override fun isBottomBarInitialised() = navBarInitialised

    override fun isLastInTab(id: Int): Boolean {
        return graphIdToTagMap.get(id) != null
    }

    override fun clear() {
        graphIdToTagMap.clear()
        tabs.clear()
        currentTabTag = null
        lastTagInTab = ""
        firstFragmentTag = ""
        firstFragmentGraphId = 0
        navBarInitialised = false
    }

    override fun getNavController(): NavController {
        return fragmentManager.fragments.last().findNavController()
    }

    override fun getCurrentFragmentManager(): FragmentManager {
        return fragmentManager.fragments.last().childFragmentManager
    }

    override fun initNavController(navController: NavController?) {
        navController?.let { this.navController = it }
    }

    override fun updateState(fragmentManager: FragmentManager, @IdRes containerId: Int) {
        this.fragmentManager = fragmentManager
        this.containerId = containerId
    }


    override fun switchTo(@NavigationRes navGraph: Int, @IdRes destination: Int, params: Bundle?) {
        //Check if we already have this host in fragment manager, case : app in the background
        if (currentTabTag != null && fragmentManager.fragments.last().tag == lastTagInTab) return

        val lastFragment = fragmentManager.fragments.last()
        currentTabTag = lastFragment.tag

        val currentHost = NavHostFragment.create(navGraph)

        val tag = UUID.randomUUID().toString()
        lastTagInTab = tag
        tabs[currentTabTag]?.add(currentTabTag!!)

        fragmentManager
            .beginTransaction()
            .apply {
                detach(lastFragment)
            }
            .setCustomAnimations(
                R.anim.nav_default_enter_anim,
                R.anim.nav_default_exit_anim,
                R.anim.nav_default_pop_enter_anim,
                R.anim.nav_default_pop_exit_anim
            )
            .add(containerId, currentHost, tag)
            .setPrimaryNavigationFragment(currentHost)
            .setReorderingAllowed(true)
            .commitNow()

        navController = currentHost.navController
        if (destination != -1) {
            navController.navigate(destination, params)
        }
    }

    override fun switchToLastGraphInBackStack() {
        val parentTag = tabs[currentTabTag]?.pollLast()
        checkNotNull(parentTag) { "Inconsistency detected. Parent Fragment lost" }

        val lastHostFragment = fragmentManager.findFragmentByTag(parentTag)
        navController = (lastHostFragment as NavHostFragment).navController
        val currentHost = fragmentManager.fragments.last() as NavHostFragment
        val currentFragmentManager = currentHost.childFragmentManager

        val isHostTag = tabs.containsKey(parentTag)
        lastTagInTab = if (isHostTag) "" else parentTag
        // In order to perform exit animation on last fragment from current flow we need to wait until
        // its onFragmentDetached call
        currentFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
                super.onFragmentDetached(fm, f)
                currentFragmentManager.unregisterFragmentLifecycleCallbacks(this)
                fragmentManager
                    .beginTransaction()
                    .remove(currentHost)
                    .commit()

            }
        }, false)

        currentFragmentManager
            .beginTransaction()
            .remove(currentFragmentManager.fragments[0])
            .commit()

        fragmentManager
            .beginTransaction()
            .attach(lastHostFragment)
            .setPrimaryNavigationFragment(lastHostFragment)
            .setReorderingAllowed(true)
            .commit()
    }

    override fun setupWithNavController(
        bottomNavigationView: BottomNavigationView,
        navGraphIds: List<Int>,
        fragmentManager: FragmentManager,
        containerId: Int,
        intent: Intent
    ): LiveData<NavController> {
        if (navBarInitialised) {
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                setOnNavigationItemSelectedListener(bottomNavigationView, item)
            }
            bottomNavigationView.setupItemReselected(graphIdToTagMap, fragmentManager)
            bottomNavigationView.setupDeepLinks(navGraphIds, fragmentManager, containerId, intent)
        } else {

            // First create a NavHostFragment for each NavGraph ID
            navGraphIds.forEachIndexed { index, navGraphId ->
                val fragmentTag = getFragmentTag(index)

                // Find or create the Navigation host fragment
                val navHostFragment = obtainNavHostFragment(
                    fragmentManager,
                    fragmentTag,
                    navGraphId,
                    containerId
                )
                tabs[fragmentTag] = LinkedList()

                // Obtain its id
                val graphId = navHostFragment.navController.graph.id

                if (index == 0) {
                    firstFragmentGraphId = graphId
                }

                // Save to the map
                graphIdToTagMap[graphId] = fragmentTag

                // Attach or detach nav host fragment depending on whether it's the selected item.
                if (bottomNavigationView.selectedItemId == graphId) {
                    // Update livedata with the selected graph
                    navController = navHostFragment.navController
                    selectedNavController.value = navController
                    attachNavHostFragment(fragmentManager, navHostFragment, index == 0)
                } else {
                    detachNavHostFragment(fragmentManager, navHostFragment)
                }
            }

            // Now connect selecting an item with swapping Fragments
            currentTabTag = graphIdToTagMap[bottomNavigationView.selectedItemId]
            firstFragmentTag = graphIdToTagMap[firstFragmentGraphId]
            val isOnFirstFragment = currentTabTag == firstFragmentTag

            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                setOnNavigationItemSelectedListener(bottomNavigationView, item)
            }
            // Optional: on item reselected, pop back stack to the destination of the graph
            bottomNavigationView.setupItemReselected(graphIdToTagMap, fragmentManager)

            // Handle deep link
            bottomNavigationView.setupDeepLinks(navGraphIds, fragmentManager, containerId, intent)

            navBarInitialised = true
        }
        return selectedNavController
    }

    override fun onBackStackChangedListener(bottomNavigationView: BottomNavigationView) {
        if (currentTabTag != firstFragmentTag && !fragmentManager.isOnBackStack(firstFragmentTag)) {
            bottomNavigationView.selectedItemId = firstFragmentGraphId
        }

        // Reset the graph if the currentDestination is not valid (happens when the back
        // stack is popped after using the back button).
        selectedNavController.value?.let { controller ->
            if (controller.currentDestination == null) {
                controller.navigate(controller.graph.id)
            }
        }
    }

//    fun getLastFragmentBeforeOnBackPressed() {
//        lastFragmentBeforeOnBackPressed = fragmentManager.fragments.last()
//    }
//
//    private var lastFragmentBeforeOnBackPressed: Fragment? = null

    private fun setOnNavigationItemSelectedListener(
        bottomNavigationView: BottomNavigationView,
        item: MenuItem
    ): Boolean {
        // Don't do anything if the state is state has already been saved.
        if (fragmentManager.isStateSaved) {
            return false
        } else {
            val newlySelectedItem = fragmentManager.findFragmentByTag(graphIdToTagMap.get(item.itemId))

            if (newlySelectedItem != null && currentTabTag != newlySelectedItem.tag) {
                newlySelectedItem as NavHostFragment
                val lastFragment = fragmentManager.fragments.last()

                checkNotNull(lastFragment.tag) { "Inconsistency detected. Fragment: $lastFragment has empty tag" }
                if (tabs[newlySelectedItem.tag]!!.size == 0 || lastFragment.tag != tabs[newlySelectedItem.tag]?.last)
                    tabs[currentTabTag]?.add(lastFragment.tag!!)

                currentTabTag = newlySelectedItem.tag

                // Pop everything above the first fragment (the "fixed start destination")
                fragmentManager.popBackStack(
                    firstFragmentTag,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )

                val lastFragmentTagInTab = tabs[newlySelectedItem.tag]?.pollLast()
                val lastFragmentInTab = fragmentManager.findFragmentByTag(lastFragmentTagInTab) ?: newlySelectedItem

                if (!fragmentManager.fragments.contains(lastFragmentInTab)) {
                    fragmentManager
                        .beginTransaction()
                        .apply {
                            detach(lastFragment)
                        }
                        .setCustomAnimations(
                            R.anim.nav_default_enter_anim,
                            R.anim.nav_default_exit_anim,
                            R.anim.nav_default_pop_enter_anim,
                            R.anim.nav_default_pop_exit_anim
                        )
                        .attach(lastFragmentInTab)
                        .addToBackStack(firstFragmentTag)
                        .setPrimaryNavigationFragment(lastFragmentInTab)
                        .setReorderingAllowed(true)
                        .commit()
                }
                navController = (lastFragmentInTab as NavHostFragment).navController
                return true
            }
        }
        return false
    }


    private fun BottomNavigationView.setupDeepLinks(
        navGraphIds: List<Int>,
        fragmentManager: FragmentManager,
        containerId: Int,
        intent: Intent
    ) {
        navGraphIds.forEachIndexed { index, navGraphId ->
            val fragmentTag = getFragmentTag(index)

            // Find or create the Navigation host fragment
            val navHostFragment = obtainNavHostFragment(
                fragmentManager,
                fragmentTag,
                navGraphId,
                containerId
            )
            // Handle Intent
            if (navHostFragment.navController.handleDeepLink(intent)
                && selectedItemId != navHostFragment.navController.graph.id
            ) {
                this.selectedItemId = navHostFragment.navController.graph.id
            }
        }
    }

    private fun BottomNavigationView.setupItemReselected(
        graphIdToTagMap: SparseArray<String>,
        fragmentManager: FragmentManager
    ) {
        setOnNavigationItemReselectedListener { item ->
            val newlySelectedItemTag = graphIdToTagMap[item.itemId]
            val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
                    as NavHostFragment
            val navController = selectedFragment.navController
            // Pop the back stack to the start destination of the current navController graph
            navController.popBackStack(
                navController.graph.startDestination, false
            )
        }
    }

    private fun detachNavHostFragment(
        fragmentManager: FragmentManager,
        navHostFragment: NavHostFragment
    ) {
        fragmentManager.beginTransaction()
            .detach(navHostFragment)
            .commitNow()
    }

    private fun attachNavHostFragment(
        fragmentManager: FragmentManager,
        navHostFragment: NavHostFragment,
        isPrimaryNavFragment: Boolean
    ) {
        fragmentManager.beginTransaction()
            .attach(navHostFragment)
            .apply {
                if (isPrimaryNavFragment) {
                    setPrimaryNavigationFragment(navHostFragment)
                }
            }
            .commitNow()

    }

    private fun obtainNavHostFragment(
        fragmentManager: FragmentManager,
        fragmentTag: String,
        navGraphId: Int,
        containerId: Int
    ): NavHostFragment {
        // If the Nav Host fragment exists, return it
        val existingFragment = fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
        existingFragment?.let { return it }

        // Otherwise, create it and return it.
        val navHostFragment = NavHostFragment.create(navGraphId)
        fragmentManager.beginTransaction()
            .add(containerId, navHostFragment, fragmentTag)
            .commitNow()
        return navHostFragment
    }

    private fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
        val backStackCount = backStackEntryCount
        for (index in 0 until backStackCount) {
            if (getBackStackEntryAt(index).name == backStackName) {
                return true
            }
        }
        return false
    }

    private fun getFragmentTag(index: Int) = "bottomNavigation#$index"

}