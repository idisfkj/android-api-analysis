package com.idisfkj.androidapianalysis

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter

/**
 * Created by idisfkj on 2019-08-02.
 * Email : idisfkj@gmail.com.
 */
object DatabindingAdapter {

    @JvmStatic
    @BindingAdapter("image_uri")
    fun setImageUri(view: ImageView, @NonNull uri: Uri) {
        view.setImageURI(uri)
    }

}