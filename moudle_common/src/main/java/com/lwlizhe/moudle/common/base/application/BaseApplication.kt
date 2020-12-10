package com.lwlizhe.moudle.common.base.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.lwlizhe.moudle.common.base.getAppViewModel
import com.lwlizhe.moudle.common.base.viewmodel.AppViewModel

open abstract class BaseApplication : Application(), ViewModelStoreOwner {

    private lateinit var mAppViewModelStore: ViewModelStore
    private lateinit var mFactory: ViewModelProvider.Factory

    protected lateinit var appConfigViewModel: AppViewModel


    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()

        mFactory = getAppFactory()
        appConfigViewModel = getAppViewModelProvider().get(AppViewModel::class.java)

        initViewModel()
    }

    /**
     * 获取一个全局的ViewModel
     */
    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, mFactory)
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }

    // 注册其他全局 ViewModel
    abstract fun initViewModel()
}
