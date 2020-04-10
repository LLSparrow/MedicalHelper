package com.llsparrow.core_base_api.filesystem

import java.io.InputStream
import java.io.OutputStream

interface FileInteractor {
    fun downloadDir(): String

    suspend fun copy(originalPath: String, destinationPath: String)

    fun copy(inputStream: InputStream, out: OutputStream): Boolean
}