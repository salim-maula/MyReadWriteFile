package com.salim.myreadwritefile

import android.content.Context
import android.util.Log
import java.io.*

internal object FileHelper {

    private val TAG = FileHelper::class.java.name

    fun writeToFile(fileModel: FileModel, context: Context){
        try {
            val outputSteamWriter = OutputStreamWriter(context.openFileOutput(fileModel.filename.toString(),Context.MODE_PRIVATE))
            outputSteamWriter.write(fileModel.data.toString())
            outputSteamWriter.close()
        }catch (e : IOException){
            Log.e(TAG, "File write failed :", e)
        }
    }
    fun readFromFile(context: Context, filename : String) : FileModel{
        val fileModel = FileModel()

        try {
            val inputStream = context.openFileInput(filename)

            if (inputStream != null){
                val receiveString = inputStream.bufferedReader().use(BufferedReader::readText)
                inputStream.close()
                fileModel.data = receiveString
                fileModel.filename = filename
            }
        }catch (e : FileNotFoundException){
            Log.e(TAG, "Dile Not Found:", e)
        }catch (e : IOException){
            Log.e(TAG, "Can not read file:", e)
        }
        return fileModel
    }
}