package com.hlt.hao.company

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hlt.hao.R

/**
 *create by zhangzhuo
 *time: 2020/7/13
 *desc
 */
class CompanyListAdapter(dataList: MutableList<CompanyItemData>)
    : BaseQuickAdapter<CompanyItemData, BaseViewHolder>(R.layout.item_company_list, data = dataList) {

    override fun convert(holder: BaseViewHolder, item: CompanyItemData) {

        holder.setText(R.id.fbdw,item.dwmc)
        holder.setText(R.id.dwbh,"单位编号：${item.dwbh}")
        holder.setText(R.id.frdb,"法人代表：${item.frdb}")

    }


}