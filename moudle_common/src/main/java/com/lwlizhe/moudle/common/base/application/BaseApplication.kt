package com.lwlizhe.moudle.common.base.application

import android.app.Application
import com.lwlizhe.moudle.common.base.viewmodel.AppViewModel

class BaseApplication : Application() {

    lateinit var appConfigViewModel: AppViewModel

    override fun onCreate() {
        super.onCreate()
        appConfigViewModel = AppViewModel(this)
    }

}