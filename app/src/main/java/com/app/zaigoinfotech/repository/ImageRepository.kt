package com.app.zaigoinfotech.repository

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class ImageRepository @Inject constructor(@ApplicationContext val context: Context) {


    fun getImagesList(imagesList: MutableLiveData<List<String>>) {
        var imagesList_: MutableList<String> = mutableListOf()
        val filePath = Environment.DIRECTORY_PICTURES
        val directory: File? =
            context.getExternalFilesDir(filePath)

        val files = directory?.listFiles()
        if (files != null) {
            for (i in files.indices) {
                Log.d(
                    "Files",
                    "FileName:" + files[i].name
                )

                val imgFile = File(directory.toString() + "/" + files[i].name)

                if (imgFile.exists()) {
                    imagesList_.add(imgFile.absolutePath)
                    //                val myBitmap =
                    //                    BitmapFactory.decodeFile(imgFile.absolutePath) //this is the bitmap for the image
                    //                val myImage: ImageView =
                    //                    findViewById<View>(R.id.imageviewTest) as ImageView //your image view in the recycler view
                    //                myImage.setImageBitmap(myBitmap) //image set to the image view
                    //  }

                }
                imagesList.postValue(imagesList_.reversed())
            }
        }

    }
}