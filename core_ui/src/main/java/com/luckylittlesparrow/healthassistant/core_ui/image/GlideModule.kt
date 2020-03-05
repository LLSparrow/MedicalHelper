//package com.luckylittlesparrow.healthassistant.core_ui.image
//
//import android.content.Context;
//import androidx.annotation.NonNull;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.GlideBuilder;
//import com.bumptech.glide.Registry;
//import com.bumptech.glide.annotation.GlideModule;
//import com.bumptech.glide.load.DecodeFormat;
//import com.bumptech.glide.module.AppGlideModule;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.samples.flickr.api.Photo;
//import java.io.InputStream;
///**
// * @author Gusev Andrei
// * @since  1.0
// */
//@GlideModule
//class FlickrGlideModule : AppGlideModule() {
//    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        registry.append(Photo::class.java, InputStream::class.java, FlickrModelLoader.Factory())
//    }
//}