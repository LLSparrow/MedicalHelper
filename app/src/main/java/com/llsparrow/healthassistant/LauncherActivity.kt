//package com.llsparrow.healthassistant
//
//import android.os.Bundle
//import android.view.View
//import android.view.animation.AnimationUtils
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.Observer
//import androidx.navigation.NavController
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.llsparrow.healthassistant.common_navigation_api.navigation.core.NavigationController
//import com.llsparrow.healthassistant.core_ui.view.*
//import com.llsparrow.healthassistant.core_ui.view.resize.FluidContentResizer
//import com.llsparrow.healthassistant.di.AppComponent
//import javax.inject.Inject
//
//class LauncherActivity : AppCompatActivity(R.layout.activity_main), BottomBarCallback {
//
//    @Inject
//    lateinit var navigationController: NavigationController
//
//    private lateinit var bottomNavigationView: BottomNavigationView
//
//    private var currentNavController: LiveData<NavController>? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.AppTheme_Light)
//        super.onCreate(savedInstanceState)
//
//        AppComponent.sInstance?.inject(this)
//        initToolbar()
//        FluidContentResizer.listen(this)
//
//        if (savedInstanceState == null) {
//            setupBottomNavigationBar(false)
//        }
//    }
//
//    override fun isFinishing(): Boolean {
//        //TODO need clear DI instead
//        val isFinishing = super.isFinishing()
//        if (isFinishing) navigationController.clear()
//        return isFinishing
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        setupBottomNavigationBar(true)
//    }
//
//    /**
//     * Called on first creation and when restoring state.
//     */
//    private fun setupBottomNavigationBar(savedInstanceState: Boolean) {
//        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        navigationController.updateState(supportFragmentManager, R.id.nav_host_container)
//
//
//        val navGraphIds = listOf(R.navigation.main_menu_graph, R.navigation.medicine_list_graph)
//
//        // Setup the bottom navigation view with a list of navigation graphs
//        val controller = navigationController.setupWithNavController(
//            bottomNavigationView,
//            navGraphIds = navGraphIds,
//            fragmentManager = supportFragmentManager,
//            containerId = R.id.nav_host_container,
//            intent = intent
//        )
//
//        // Whenever the selected controller changes, setup the action bar.
//        controller.observe(this, Observer { navController ->
//            // setupActionBarWithNavController(navController)
//            // navigationController.initNavController(navController)
//            // if (savedInstanceState) navigationController.onRestoreInstanceState()
//        })
//        currentNavController = controller
//
//        supportFragmentManager.addOnBackStackChangedListener {
//            navigationController.onBackStackChangedListener(bottomNavigationView)
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return currentNavController?.value?.navigateUp() ?: false
//    }
//
//    override fun showBottomNavigationBar() {
//        if (bottomNavigationView.visibility == View.GONE) {
//            bottomNavigationView.show()
//            val animSlideDown = AnimationUtils.loadAnimation(this, R.anim.slide_up)
//            bottomNavigationView.startAnimation(animSlideDown)
//        }
//    }
//
//    override fun hideBottomNavigationBar() {
//        if (bottomNavigationView.visibility == View.VISIBLE) {
//            bottomNavigationView.gone()
//            val animSlideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
//            bottomNavigationView.startAnimation(animSlideDown)
//        }
//    }
//
//    override fun onBackPressed() {
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container)
//
//        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
//
//        if (currentFragment != null && (currentFragment as BaseFragment<*>).onBackPressed()) return
//        else {
////            navigationController.checkBackStackForLastTab()
//            super.onBackPressed()
//        }
//    }
//
//    private fun initToolbar() {
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        val actionBar = supportActionBar
////        if (actionBar != null) {
////            actionBar.setDisplayHomeAsUpEnabled(true)
////            actionBar.setDisplayShowHomeEnabled(true)
////        }
//    }
//}