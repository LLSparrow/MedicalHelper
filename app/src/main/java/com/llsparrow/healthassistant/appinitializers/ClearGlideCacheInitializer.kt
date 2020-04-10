//package com.luckylittlesparrow.healthassistant.core_base_impl.appinitializers
//
//import android.app.Application
//import com.luckylittlesparrow.core_base_api.appinitializers.AppInitializer
//import java.io.File
//
//class ClearGlideCacheInitializer constructor(
//    private val deleteFolder: DeleteFolder
//) : AppInitializer {
//    override fun init(application: Application) {
//        val dir = File(application.cacheDir, "image_manager_disk_cache")
//        deleteFolder(DeleteFolder.Params(dir))
//    }
//}