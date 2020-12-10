package com.lwlizhe.novelvideomodulecommon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lwlizhe.moudle.common.base.entity.AppGlobalStateConfig
import com.lwlizhe.moudle.common.base.ui.BaseActivity
import com.lwlizhe.moudle.common.base.viewmodel.BaseViewModel

class MainActivity : BaseActivity<BaseViewModel>() {
    override fun getRootViewResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun onConfigDataChanged(configData: AppGlobalStateConfig) {
    }
}
