package com.gachon.garamgaebi2.views.thread

import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityThreadBinding

class ThreadActivity : BaseActivity<ActivityThreadBinding>(ActivityThreadBinding::inflate) {

    override fun initView() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, ThreadFragment()).commit()
        initListener()

    }

    private fun initListener(){

    }


}