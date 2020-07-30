package com.hlt.hao

import android.content.Intent
import com.hlt.hao.grain.add.AddGrainActivity
import com.hlt.hao.grain.list.GrainListActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 *create by zhangzhuo
 *time: 2020/7/29
 *desc
 */
class HomeActivity : BaseActivity() {
    override fun initView() {
        homeAdd.setOnClickListener {
            startActivity(Intent(applicationContext, AddGrainActivity::class.java))
        }

        homeList.setOnClickListener {
            startActivity(Intent(applicationContext, GrainListActivity::class.java))
        }

        ivBack.setOnClickListener {
            finish()
        }

    }

    override fun getLayoutId() = R.layout.activity_home
}