package com.keove.library.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.commons.io.FilenameUtils
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL


fun File.loadFromUrl(url: String?, callback: ((Boolean) -> Unit)?) {

    if (url == null) {
        callback?.let { it(false) }
        return
    }

    if (exists()) {
        callback?.let { it(true) }
        return
    }

    GlobalScope.launch(Dispatchers.IO) {

        try {
            val folderPath = FilenameUtils.getFullPath(absolutePath)
            val folder = File(folderPath)
            if (!folder.exists()) {
                folder.mkdirs()
            }

            var downloadedSize = 0

            val url = URL(url)
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()
            val fileOutput = FileOutputStream(this@loadFromUrl)
            val inputStream = urlConnection.inputStream
            val buffer = ByteArray(1024)
            var bufferLength = 0
            while (inputStream.read(buffer).also { bufferLength = it } > 0) {
                fileOutput.write(buffer, 0, bufferLength)
                downloadedSize += bufferLength
            }
            fileOutput.close()

            GlobalScope.launch(Dispatchers.Main) {
                callback?.let {
                    it(true)
                }
            }

        } catch (e: Exception) {
            GlobalScope.launch(Dispatchers.Main) {
                callback?.let {
                    it(false)
                }
            }
        }
        catch (e:Exception) {
            GlobalScope.launch(Dispatchers.Main) {
                callback?.let {
                    it(false)
                }
            }
        }

    }
}

fun File.loadFromUrl(url: String?): Boolean {

    try {
        if (url == null) {
            return false
        }

        if (exists()) {
            return true
        }

        val folderPath = FilenameUtils.getFullPath(absolutePath)
        val folder = File(folderPath)
        if (!folder.exists()) {
            folder.mkdirs()
        }

        var downloadedSize = 0

        val remoteURL = URL(url)
        val urlConnection = remoteURL.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.connect()
        val fileOutput = FileOutputStream(this@loadFromUrl)
        val inputStream = urlConnection.inputStream
        val buffer = ByteArray(1024)
        var bufferLength = 0
        while (inputStream.read(buffer).also { bufferLength = it } > 0) {
            fileOutput.write(buffer, 0, bufferLength)
            downloadedSize += bufferLength
        }
        fileOutput.close()

        return true
    } catch (ex: Exception) {
        return false
    }

}