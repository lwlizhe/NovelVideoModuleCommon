package com.lwlizhe.moudle.common.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.lwlizhe.moudle.common.base.entity.AppGlobalStateConfig

open class AppViewModel(application: Application) : AndroidViewModel(application) {

    private final var appConfigData: MutableLiveData<AppGlobalStateConfig> = MutableLiveData()

    fun getConfigLiveData(): MutableLiveData<AppGlobalStateConfig> {
        return appConfigData
    }

}