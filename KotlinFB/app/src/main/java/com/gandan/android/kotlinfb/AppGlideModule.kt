package com.gandan.android.kotlinfb

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.module.AppGlideModule
import com.google.firebase.storage.StorageReference
import com.firebase.ui.storage.images.FirebaseImageLoader
import java.io.InputStream


/**
 * Created by XPS on 2018-05-17.
 */
@GlideModule
class AppGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.append(StorageReference::class.java, InputStream::class.java, com.gandan.android.kotlinfb.FirebaseImageLoader.Factory())
    }
}