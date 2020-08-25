package com.hlt.hao.grain.list

import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hlt.hao.AppConfig
import com.hlt.hao.BaseActivity
import com.hlt.hao.R
import com.hlt.hao.grain.add.Data
import kotlinx.android.synthetic.main.activity_grain_images.*
import java.util.ArrayList

/**
 *create by zhangzhuo
 *time: 2020/8/25
 *desc 订单多张图片浏览
 */
class GrainImageViewActivity : BaseActivity() {
    override fun initView() {

        val parcelableArrayListExtra = intent.getParcelableArrayListExtra<Data>("images")
        rvGrainImages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvGrainImages.adapter = GrainImageAdapter(parcelableArrayListExtra)
    }

    override fun getLayoutId() = R.layout.activity_grain_images
}


class GrainImageAdapter(dataList: ArrayList<Data>?) : BaseQuickAdapter<Data, BaseViewHolder>(R.layout.dialog_show_big_image,dataList) {
    override fun convert(holder: BaseViewHolder, item: Data) {
        val fullUrl = AppConfig.BASE_URL.plus(item.url)
        Glide.with(context).load(fullUrl).into(holder.getView(R.id.dialogImageGrain))

    }

}