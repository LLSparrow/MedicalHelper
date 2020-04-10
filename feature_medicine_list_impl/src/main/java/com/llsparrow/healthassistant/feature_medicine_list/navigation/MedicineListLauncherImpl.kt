package com.llsparrow.healthassistant.feature_medicine_list.navigation

import com.llsparrow.core_base_api.navigation.NavigationController
import com.llsparrow.healthassistant.feature_medicine_list.R
import com.llsparrow.healthassistant.feature_medicine_list_api.navigation.MedicineListLauncher

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class MedicineListLauncherImpl constructor(
    private val navigationController: NavigationController
) : MedicineListLauncher {

    override fun launchFlow() {
        navigationController.switchTo(R.navigation.medicine_list_graph)
    }
}