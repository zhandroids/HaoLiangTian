package com.hlt.hao.company

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hlt.hao.BaseActivity
import com.hlt.hao.R
import com.hlt.hao.grain.add.AddGrainActivity
import kotlinx.android.synthetic.main.activity_grain_list.*

/**
 *create by zhangzhuo
 *time: 2020/7/13
 *desc：发布单位列表页面
 */
class CompanyListActivity : BaseActivity() {

    private var pageNo = 1
    private val pageSize = 20

    private val viewModel: GrainListViewModel by viewModels()

    private lateinit var companyListAdapter: CompanyListAdapter
    private var dataList: MutableList<CompanyItemData> = mutableListOf()

    override fun initView() {

        swipeRefreshLayout.setOnRefreshListener {

            pageNo = 1
            getData()
        }

        companyListAdapter = CompanyListAdapter(dataList)

//        grainListAdapter.addLoadMoreModule()

        rvGrainList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvGrainList.adapter = companyListAdapter

        getData()

        companyListAdapter.setOnItemClickListener { adapter, view, position ->

            val intent = intent
            intent.putExtra("data", dataList[position])
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        ivBack.setOnClickListener {
            finish()
        }

    }

    private fun getData() {
        viewModel.getGrainList(pageNo = pageNo.toString(), pageSize = pageSize.toString()).observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            if (it.isState) {
                val data = it.data
                if (data.success!!) {
                    if (data != null) {
                        if (data.data != null) {
                            if (data.data.isNotEmpty()) {
                                dataList = data.data
                                companyListAdapter.setList(dataList)
                            }
                        }
                    }
                }
            }
        })
    }

    override fun getLayoutId() = R.layout.activity_company_list

}