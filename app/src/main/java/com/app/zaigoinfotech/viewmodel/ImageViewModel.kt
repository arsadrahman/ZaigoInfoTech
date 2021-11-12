package com.app.zaigoinfotech.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.zaigoinfotech.repository.ImageRepository
import com.app.zaigoinfotech.repository.OfflineRepository
import com.app.zaigoinfotech.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : ViewModel() {

    var imagesList: MutableLiveData<List<String>> = MutableLiveData()

    fun getImages() {
        imageRepository.getImagesList(imagesList)
    }

}