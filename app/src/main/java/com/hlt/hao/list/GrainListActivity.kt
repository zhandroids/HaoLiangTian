package com.hlt.hao.list

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hlt.hao.BaseActivity
import com.hlt.hao.R
import kotlinx.android.synthetic.main.activity_grain_list.*

/**
 *create by zhangzhuo
 *time: 2020/7/13
 *desc：粮食订单发布列表
 */
class GrainListActivity : BaseActivity() {

    private var pageNo = 1
    private val pageSize = 20

    private val viewModel:GrainListViewModel by viewModels()

    private lateinit var grainListAdapter:GrainListAdapter
    private  var dataList:MutableList<GrainListData> = mutableListOf()

    override fun initView() {

        swipeRefreshLayout.setOnRefreshListener {

            pageNo = 1
            getData()
        }

        grainListAdapter = GrainListAdapter(dataList)

//        grainListAdapter.addLoadMoreModule()

        rvGrainList.layoutManager  = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

rvGrainList.adapter = grainListAdapter

        getData()
    }

    fun getData(){
        viewModel.getGrainList(pageNo = pageNo.toString(),pageSize = pageSize.toString()).observe(this, Observer {
           swipeRefreshLayout.isRefreshing = false
           Log.e("asker","11111111111")
            if (it.isState){
                Log.e("asker","22222222222222222")

                val data = it.data
                if (data.success!!){
                    Log.e("asker","33333333333333")

                    if (data!=null){
                        if (data.data!=null){
                            if (data.data.list?.isNotEmpty()!!){
                                dataList = data.data.list
                                grainListAdapter.setNewInstance(dataList)
                            }
                        }
                    }


                }


            }

        })
    }

    override fun getLayoutId() = R.layout.activity_grain_list

}