package com.lwlizhe.moudle.common.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.lwlizhe.moudle.common.base.entity.AppGlobalStateConfig

open class AppViewModel : BaseViewModel() {

    private val appConfigData: MutableLiveData<AppGlobalStateConfig> = MutableLiveData()

    fun getConfigLiveData(): MutableLiveData<AppGlobalStateConfig> {
        return appConfigData
    }

}