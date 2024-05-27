package com.keove.library.extensions

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import android.view.Window


fun View.toBitmap(onBitmapReady: (Bitmap?) -> Unit) {

    try {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val temporalBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)

            val window: Window = (this.context as Activity).window

            val location = IntArray(2)

            this.getLocationInWindow(location)

            val viewRectangle = Rect(location[0], location[1], location[0] + this.width, location[1] + this.height)

            val onPixelCopyListener: PixelCopy.OnPixelCopyFinishedListener = PixelCopy.OnPixelCopyFinishedListener { copyResult ->

                if (copyResult == PixelCopy.SUCCESS) {

                    onBitmapReady(temporalBitmap)
                } else {

                    error("Error while copying pixels, copy result: $copyResult")
                }
            }

            PixelCopy.request(window, viewRectangle, temporalBitmap, onPixelCopyListener, Handler(
                Looper.getMainLooper()))
        } else {

            val temporalBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.RGB_565)

            val canvas = android.graphics.Canvas(temporalBitmap)

            this.draw(canvas)

            canvas.setBitmap(null)

            onBitmapReady(temporalBitmap)
        }

    }
    catch (exception: Exception) {
        onBitmapReady(null)
    }
}
