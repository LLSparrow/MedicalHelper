package com.llsparrow.core_base_api.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

/**
 * Router class, which performs transition within one module
 *
 * @param navigationController performs transitions within current module
 *
 * @author Gusev Andrei
 * @since  1.0
 */
abstract class BaseRouter(
    private val navigationController: NavigationController
) {

    /**
     * Perform navigation to [destination].
     *
     * @param destination an {@link NavDestination#getAction(int) action} id or a destination id to
     *              navigate to
     * @param args arguments to pass to the destination
     * @param navOptions special options for this navigation operation
     * @param extras extras to pass to the Navigator
     */
    fun navigate(
        @IdRes destination: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        @Nullable extras: Navigator.Extras? = null
    ) {
        navigationController.getNavController().navigate(destination, args, navOptions, extras)
    }

    /**
     * Perform back transition, if it's last step in flow, [exitFlow] will be called
     * in order to switch to previous navigation graph.
     */
    fun onNavigationBackPressed() {
        if (navigationController.getCurrentFragmentManager().backStackEntryCount == 0) {
            exitFlow()
        } else {
            navigationController.getNavController().popBackStack()
        }
    }

    /**
     * @return Current navigation step.
     */
    fun currentStep(): Int? = navigationController.getNavController().currentDestination?.id

    /**
     * Switch to previous navigation graph.
     */
    fun exitFlow() {
        navigationController.switchToLastGraphInBackStack()
    }
}