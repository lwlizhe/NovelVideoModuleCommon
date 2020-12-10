package com.lwlizhe.moudle.common.base.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lwlizhe.moudle.common.base.entity.AppGlobalStateConfig
import com.lwlizhe.moudle.common.base.getAppViewModel
import com.lwlizhe.moudle.common.base.viewmodel.AppViewModel
import com.lwlizhe.moudle.common.base.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    protected var currentViewModel: BaseViewModel? = null
    private val appConfigViewModel: AppViewModel = getAppViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configViewModel()
        if (getRootViewResId()!=0) {
            setContentView(getRootViewResId())
        } else {
            setContentView(initRootView())
        }
        initView()
        initListener()
        initData()
    }

    protected fun configViewModel() {

        appConfigViewModel.getConfigLiveData().observe(this,
            Observer<AppGlobalStateConfig> { config -> onConfigDataChanged(config) })

        currentViewModel = getViewModel()

    }

    protected abstract fun getRootViewResId(): Int

    protected fun initRootView(): View? {
        return null
    }

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun initListener()

    protected abstract fun getViewModel(): VM

    protected abstract fun onConfigDataChanged(configData: AppGlobalStateConfig)
}