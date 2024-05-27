package com.keove.library.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.Log
import org.apache.commons.io.FilenameUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.math.roundToInt


fun Bitmap.blur(context: Context?,scale:Float = 0.4f,radius:Float = 7.5f): Bitmap? {
    val width = (this.width * scale).roundToInt()
    val height = (this.height * scale).roundToInt()
    val inputBitmap = Bitmap.createScaledBitmap(this, width, height, false)
    val outputBitmap = Bitmap.createBitmap(inputBitmap)
    val rs = RenderScript.create(context)
    val theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
    val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
    val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
    theIntrinsic.setRadius(radius)
    theIntrinsic.setInput(tmpIn)
    theIntrinsic.forEach(tmpOut)
    tmpOut.copyTo(outputBitmap)
    return outputBitmap
}


fun Bitmap.saveToFile(path:String): File? {
    return try {

        val file = File(path)
        val folderPath = FilenameUtils.getFullPath(file.absolutePath)
        val folder = File(folderPath)
        if(!folder.exists()) {
            folder.mkdirs()
        }

        val bytes = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 60, bytes)

        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()
        val fo = FileOutputStream(file)
        fo.write(bytes.toByteArray())
        fo.close()
        file
    } catch (ex: Exception) {
        Log.e("BitmapExtensions","Bitmap Save Error ${ex.localizedMessage}")
        null
    }
}

fun Bitmap.correctExif(context: Context, file:File):Bitmap {

    val input: InputStream? = context.contentResolver.openInputStream(Uri.fromFile(file))
    val ei: ExifInterface = if (Build.VERSION.SDK_INT > 23 && input != null) ExifInterface(input) else ExifInterface(file.path)

    return when (ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)) {
        ExifInterface.ORIENTATION_ROTATE_90 -> this.rotate(90)
        ExifInterface.ORIENTATION_ROTATE_180 -> this.rotate(180)
        ExifInterface.ORIENTATION_ROTATE_270 -> this.rotate(270)
        else -> this
    }


}

fun Bitmap.rotate(degree: Int): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(degree.toFloat())
    val rotatedImg = Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
    this.recycle()
    return rotatedImg
}