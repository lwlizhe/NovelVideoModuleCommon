package com.lwlizhe.moudle.common.base.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lwlizhe.moudle.common.base.entity.AppGlobalStateConfig
import com.lwlizhe.moudle.common.base.viewmodel.AppViewModel
import com.lwlizhe.moudle.common.base.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var currentViewModel: BaseViewModel
    protected lateinit var appConfigViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configViewModel()
        setContentView(initRootView())
        initView()
        initListener()
        initData()
    }

    protected fun configViewModel(){

        appConfigViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        currentViewModel = getViewModel()

        val configObserver: Observer<AppGlobalStateConfig> = Observer {
            onGetConfigData(it)
        }

        appConfigViewModel.getConfigLiveData().observe(this,configObserver)

    }

    protected abstract fun initRootView(): View

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun initListener()

    protected abstract fun getViewModel(): VM

    protected fun onGetConfigData(configData:AppGlobalStateConfig ){

    }
}