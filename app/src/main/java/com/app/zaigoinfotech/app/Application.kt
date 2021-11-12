package com.app.zaigoinfotech.app

import android.app.Application
import android.support.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : MultiDexApplication() {


}