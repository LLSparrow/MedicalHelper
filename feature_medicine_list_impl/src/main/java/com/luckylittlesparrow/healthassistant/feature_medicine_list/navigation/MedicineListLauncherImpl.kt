package com.luckylittlesparrow.healthassistant.feature_medicine_list.navigation

import com.luckylittlesparrow.core_base_api.navigation.NavigationController
import com.luckylittlesparrow.healthassistant.feature_medicine_list.R
import com.luckylittlesparrow.healthassistant.feature_medicine_list_api.navigation.MedicineListLauncher

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