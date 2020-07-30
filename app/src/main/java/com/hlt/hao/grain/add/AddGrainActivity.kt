package com.hlt.hao.grain.add

import android.app.DatePickerDialog
import android.content.Intent
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.viewModels
import com.hlt.hao.BaseActivity
import com.hlt.hao.R
import com.hlt.hao.company.CompanyItemData
import com.hlt.hao.company.CompanyListActivity
import kotlinx.android.synthetic.main.activity_add_grain.*
import java.lang.Exception
import java.util.*

/**
 *create by zhangzhuo
 *time: 2020/7/26
 *desc
 */
class AddGrainActivity : BaseActivity() {

    val viewModel: AddGrainViewModel by viewModels()

    var selectDate: String? = null

    override fun initView() {

        sqrq.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        selectDate = "$year-${month + 1}-$dayOfMonth"
                        sqrq.text = selectDate
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)

            )

            datePickerDialog.show()
        }

        //选择发布单位
        fbdw.setOnClickListener {
            startActivityForResult(Intent(applicationContext, CompanyListActivity::class.java), 0x201)
        }

        btnAdd.setOnClickListener {

            if (fbdw.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext,
                        "请选择单位", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (lspz.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext,
                        "请输入粮食品种", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (djyq.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext,
                        "请输入粮食质量要求", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (zzqy.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext,
                        "请输入种植区域", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (zzzq.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext,
                        "请输入种植周期", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (ddds.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext,
                        "请输入订单吨数", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (sqrq.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext,
                        "请选择申请日期", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.add(dwmc = fbdw.text.toString(),
                    lspz = lspz.text.toString(),
                    lszlyq = djyq.text.toString(),
                    zzqy = zzqy.text.toString(),
                    zzzq = zzzq.text.toString(),
                    ddds = ddds.text.toString(),
                    ddyxq = ddyxq.text.toString(),
                    tjsj = sqrq.text.toString()
            ).observe(this, androidx.lifecycle.Observer {
                if (it.isState){
                    if (it.data.success) {
                        Toast.makeText(applicationContext,"发布成功",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext,"发布失败",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"发布失败",Toast.LENGTH_SHORT).show()

                }
            })


        }

    }

    override fun getLayoutId() = R.layout.activity_add_grain

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0x201) {

           try {
               val companyItemData = data?.getSerializableExtra("data") as CompanyItemData
               fbdw.text = companyItemData.dwmc
           }catch (e:Exception){

           }

        }


    }

    fun checkParams() {

    }

    companion object {
        val TAG = AddGrainActivity::class.simpleName
    }
}