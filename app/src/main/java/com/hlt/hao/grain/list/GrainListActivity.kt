package com.hlt.hao.grain.list

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hlt.hao.BaseActivity
import com.hlt.hao.R
import com.hlt.hao.company.CompanyListActivity
import com.hlt.hao.grain.add.AddGrainActivity
import com.hlt.hao.grain.add.UploadImageActivity
import com.hlt.hao.http.LiveDataResponse
import kotlinx.android.synthetic.main.activity_grain_list.*

/**
 *create by zhangzhuo
 *time: 2020/7/13
 *desc：粮食订单发布列表
 */
class GrainListActivity : BaseActivity() {

    private var pageNo = 1
    private val pageSize = 20

    private val viewModel: GrainListViewModel by viewModels()

    private lateinit var grainListAdapter: GrainListAdapter
    private var dataList: MutableList<GrainListData> = mutableListOf()

    override fun initView() {

        swipeRefreshLayout.setOnRefreshListener {

            pageNo = 1
            getData()
        }

        grainListAdapter = GrainListAdapter(dataList)

//        grainListAdapter.addLoadMoreModule()

        rvGrainList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvGrainList.adapter = grainListAdapter

        getData()

//        grainListAdapter.setOnItemLongClickListener { adapter, view, position ->
//
//            showEditItemDialog()
//
//            true
//        }

        grainListAdapter.loadMoreModule.setOnLoadMoreListener {
            swipeRefreshLayout.isRefreshing = false
            grainListAdapter.loadMoreModule.isEnableLoadMore = true
            pageNo += pageNo
            getData()

        }
        grainListAdapter.addChildClickViewIds(R.id.itemDelete, R.id.itemAddImage,R.id.viewImage)

        grainListAdapter.setOnItemChildClickListener { adapter, view, position ->

            if (view.id == R.id.itemAddImage) {
                //上传图片
                Intent(applicationContext, UploadImageActivity::class.java).let { intent->
                    intent.putExtra("refId",dataList[position].id.toString())
                    startActivity(intent)
                }
            } else if (view.id == R.id.itemDelete) {
                //删除订单
                viewModel.deleteOrder(dataList[position].id.toString()).observe(this, Observer {
                    if (it.isState) {
                        if (it.data.success) {
                            Toast.makeText(applicationContext, "删除成功", Toast.LENGTH_SHORT).show()
                            getData()
                        } else {
                            Toast.makeText(applicationContext, "删除失败", Toast.LENGTH_SHORT).show()

                        }
                    }

                })
            }else if (view.id==R.id.viewImage){
                //查看图片
                viewModel.getSingleImage(dataList[position].id.toString()).observe(this, Observer {
                    if (it.isState) {
                        if (it.data.success) {
                            Toast.makeText(applicationContext, "获取", Toast.LENGTH_SHORT).show()
                            getData()
                        } else {
                            Toast.makeText(applicationContext, "失败", Toast.LENGTH_SHORT).show()

                        }
                    }

                })
            }
        }
        add.setOnClickListener {
            startActivity(Intent(applicationContext, AddGrainActivity::class.java))
        }
    }

    /**
     * 展示编辑选项
     */
    fun showEditItemDialog() {

        val dialogItems = arrayOf("删除", "修改")

        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)

        dialog.setTitle("请选择操作")

        dialog.setItems(dialogItems) { dialog, which ->

            when (which) {

                0 -> {

                }

                1 -> {

                }

            }


        }

        dialog.create().show()


    }

    fun getData() {
        viewModel.getGrainList(pageNo = pageNo.toString(), pageSize = pageSize.toString()).observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            grainListAdapter.loadMoreModule.loadMoreComplete()
            Log.e("asker", "11111111111")
            if (it.isState) {
                val data = it.data
                if (data.success!!) {
                    Log.e("asker", "33333333333333")

                    if (data != null) {
                        if (data.data != null) {
                            if (data.data.list?.isNotEmpty()!!) {
                                dataList = data.data.list

                                grainListAdapter.loadMoreModule.isEnableLoadMore = dataList.size >= pageSize

                                if (pageNo == 1) {
                                    //如果为刷新
                                    grainListAdapter.setList(dataList)

                                } else {
                                    grainListAdapter.addData(dataList)
                                }
//                                grainListAdapter.setList(dataList)
                            }
                        }
                    }

                }
            }

        })
    }

    override fun getLayoutId() = R.layout.activity_grain_list

}