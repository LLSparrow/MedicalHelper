package com.llsparrow.healthassistant.core_base_impl.filesystem

import android.os.Environment
import com.llsparrow.core_base_api.filesystem.FileInteractor
import com.llsparrow.core_base_api.io.AppDispatchers
import io.reactivex.Completable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileInteractorImpl @Inject constructor(private val dispatchers: AppDispatchers) : FileInteractor {

    override fun downloadDir(): String {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath

        val file = File(path)
        if (!file.exists()) file.mkdirs()

        return path
    }

    override suspend fun copy(originalPath: String, destinationPath: String) {
        withContext(dispatchers.IO) {
            val inputStream = FileInputStream(originalPath)
            val outputStream = FileOutputStream(destinationPath)
            val byteArray = ByteArray(inputStream.available())
            inputStream.read(byteArray)
            outputStream.write(byteArray)
            outputStream.flush()
            inputStream.close()
            outputStream.close()
        }
    }

    override fun copy(inputStream: InputStream, out: OutputStream): Boolean {
        val buffer = ByteArray(1024)
        var read = inputStream.read(buffer)
        while (read != -1) {
            out.write(buffer, 0, read)
            read = inputStream.read(buffer)
        }
        return true
    }
}