package com.app.zaigoinfotech.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zaigoinfotech.BuildConfig
import com.app.zaigoinfotech.R
import com.app.zaigoinfotech.databinding.ActivityImageAddBinding
import com.app.zaigoinfotech.view.adapter.ImageAdapter
import com.app.zaigoinfotech.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class ImageAdd : AppCompatActivity() {

    val imageViewModel: ImageViewModel by viewModels()
    private lateinit var activityImageAddBinding: ActivityImageAddBinding

    var userId: Int = 0
    lateinit var uri: Uri
    lateinit var currentPhotoPath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityImageAddBinding = DataBindingUtil.setContentView<ActivityImageAddBinding>(
            this,
            R.layout.activity_image_add
        )
        userId = intent.getIntExtra("id", 0)
        if (checkAndRequestPermissions(this)) {
            imageViewModel.getImages()
            imageViewModel.imagesList.observe(this, androidx.lifecycle.Observer { images ->
                val imageAdapter: ImageAdapter = ImageAdapter(this, images)
                val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                activityImageAddBinding.recyclerView.layoutManager = layoutManager
                activityImageAddBinding.recyclerView.adapter = imageAdapter
            })
        } else {
            Toast.makeText(this, "Need Permission to Use this Feature", Toast.LENGTH_SHORT).show()
            finish()
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menucamera, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuitem -> {
                captureCameraAndUpload()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun captureCameraAndUpload() {

        uri = FileProvider.getUriForFile(
            Objects.requireNonNull(applicationContext),
            BuildConfig.APPLICATION_ID + ".provider", createImageFile()
        );
        getCameraImage.launch(uri)
    }

    private val getCameraImage =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                //Refresh the image List
                Toast.makeText(this, "Image Updated Successfully", Toast.LENGTH_SHORT).show()
                imageViewModel.getImages()
            }
        }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    //Permission
    open fun checkAndRequestPermissions(context: Activity?): Boolean {
        val storePermission = ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val cameraPermission = ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.CAMERA
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (storePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                .add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                context!!, listPermissionsNeeded
                    .toTypedArray(),
                101
            )
            return false
        }
        return true
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            101 -> if (ContextCompat.checkSelfPermission(
                    this@ImageAdd,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    applicationContext,
                    "This App Requires Access to Camara.", Toast.LENGTH_SHORT
                )
                    .show()
            } else if (ContextCompat.checkSelfPermission(
                    this@ImageAdd,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    applicationContext,
                    "This App Requires Access to Your Storage.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                //Success
            }
        }
    }


}