package com.example.crownstack_project.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.crownstack_project.model.Songs
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import java.io.IOException
import javax.xml.transform.Result


class SongsViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    val contentLiveData = MutableLiveData<Songs>()

    companion object {
        private val TAG = SongsViewModel::class.java.simpleName
    }


    fun getSongsData(fileName: String) = viewModelScope.launch {
        val text : String? = withContext(Dispatchers.IO){
            readTextFile(fileName)
        }
        if (text != null){
            val gson = Gson()
            val p: Songs = gson.fromJson(text, Songs::class.java)
            contentLiveData.postValue(p)

        }
    }

    fun readTextFile(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().readText()
        } catch (exception: FileNotFoundException) {
            Log.d(
                TAG,
                exception.message ?: "$fileName not found in directory")
            null
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: "Error saving file $fileName")
            null
        }
    }
}