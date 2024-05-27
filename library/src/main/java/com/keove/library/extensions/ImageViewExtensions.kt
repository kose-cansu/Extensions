package com.keove.library.extensions

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File


fun ImageView.load(url: String?, resId: Int) {
    url?.let {
        Glide.with(this).load(url).centerCrop().placeholder(resId).into(this)
    } ?: setImageResource(resId)


}

fun ImageView.loadFitXY(urlXY:String?, resId: Int) {
    setImageResource(resId)
    Glide.with(context)
        .asBitmap()
        .load(urlXY)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                val drawable: Drawable = BitmapDrawable(context.resources, bitmap)
                setImageDrawable(drawable)
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
}

@BindingAdapter("urlXY","placeholder")
fun ImageView.loadFitXY(urlXY:String?, placeholder: Drawable) {
    setImageDrawable(placeholder)
    Glide.with(context)
        .asBitmap()
        .load(urlXY)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                val drawable: Drawable = BitmapDrawable(context.resources, bitmap)
                setImageDrawable(drawable)
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
}

fun ImageView.loadImage(url: String?, resId: Int) {
    url?.let {
        Glide.with(this).load(url).placeholder(resId).into(this)
    } ?: setImageResource(resId)


}

fun ImageView.loadResized(url:String?, width:Float, resId: Int,isAlive:Boolean) {
    url?.let {
        if(!isAlive) {
            return
        }
        Glide.with(this).load(url.applyQueryParameters(hashMapOf("width" to width.toInt().toString()))
        ).override(width.toInt()).placeholder(resId).into(this)
    } ?: setImageResource(resId)
}

@BindingAdapter("url","placeholder")
fun ImageView.load(url:String?, placeholder:Drawable) {
    url?.let {
        Glide.with(this).load(url).centerCrop().placeholder(placeholder).into(this)
    } ?: setImageDrawable(placeholder)
}

fun ImageView.load(url: String?) {
    url?.let {
        Glide.with(this).load(url).centerCrop().into(this)
    }


}

fun ImageView.loadNoTransform(url: String?,isAlive:Boolean) {
    url?.let {
        if(!isAlive) {
            return
        }
        Glide.with(this).load(url).into(this)
    }


}



fun ImageView.loadCircle(url: String?, resId: Int,isAlive:Boolean) {
    url?.let {
        if(!isAlive) {
            return
        }
        Glide.with(this).load(url).circleCrop().placeholder(resId).into(this)
    } ?: setImageResource(resId)
}

fun ImageView.loadCircle(url: String?,isAlive:Boolean) {
    url?.let {
        if(!isAlive) {
            return
        }
        Glide.with(this).load(url).circleCrop().into(this)
    }
}

fun ImageView.load(uri: Uri?, resId: Int) {
    uri?.let {
        Glide.with(this).load(uri).placeholder(resId).into(this)
    } ?: setImageResource(resId)

}

fun ImageView.loadCircle(uri: Uri?, resId: Int) {
    uri?.let {
        Glide.with(this).load(uri).circleCrop().placeholder(resId).into(this)
    } ?: setImageResource(resId)

}

fun ImageView.load(file: File?, resId: Int) {
    file?.let {
        Glide.with(this).load(Uri.fromFile(file)).placeholder(resId).into(this)
    } ?: setImageResource(resId)

}

fun ImageView.loadCircle(file: File?, resId: Int) {
    file?.let {
        Glide.with(this).load(Uri.fromFile(file)).placeholder(resId).circleCrop().into(this)
    } ?: setImageResource(resId)

}

