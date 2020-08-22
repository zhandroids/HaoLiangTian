package com.hlt.hao.grain.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hlt.hao.R

/**
 *create by zhangzhuo
 *time: 2020/7/13
 *desc
 */
class GrainListAdapter(dataList: MutableList<GrainListData>)
    : BaseQuickAdapter<GrainListData, BaseViewHolder>(R.layout.item_grain_list, data = dataList), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: GrainListData) {

        holder.setText(R.id.fbdw, "发布单位：${item.dwmc}")
        holder.setText(R.id.fbbh, "发布编号：${item.lsddfbbh}")
        holder.setText(R.id.lspz, "粮食品种：${item.lspz}")
        holder.setText(R.id.djyq, "质量等级要求：${item.lszlyq}")
        holder.setText(R.id.zzqy, "种植区域：${item.zzqy}")
        holder.setText(R.id.ddds, "订单吨数：${item.ddds} 吨")
        holder.setText(R.id.zzzq, "种植周期：${item.zzzq}")
        holder.setText(R.id.ddyxq, "订单有效期：${item.ddyxq}天")
        holder.setText(R.id.sqrq, "申请日期：${item.tjsj}")
        holder.setText(R.id.tjdw, "提交单位：${item.jsdwmc}")

    }


}