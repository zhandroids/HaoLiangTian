package com.hlt.hao.grain.add

import android.Manifest
import android.R.attr
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.hlt.hao.BaseActivity
import com.hlt.hao.R
import com.hlt.hao.company.CompanyItemData
import com.hlt.hao.company.CompanyListActivity
import com.hlt.hao.utils.GlideLoadEngine
import com.tbruyelle.rxpermissions3.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.filter.Filter
import com.zhihu.matisse.internal.entity.CaptureStrategy
import com.zhihu.matisse.internal.entity.IncapableCause
import com.zhihu.matisse.internal.entity.Item
import kotlinx.android.synthetic.main.activity_add_grain.*
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*


/**
 *create by zhangzhuo
 *time: 2020/7/26
 *desc
 */
class AddGrainActivity : BaseActivity() {

    val viewModel: AddGrainViewModel by viewModels()

    var selectDate: String? = null
    val REQUEST_CODE_CHOOSE_PHOTO_ALBUM = 0x101

//    lateinit var rxPermissions:RxPermissions

    override fun initView() {
//        rxPermissions = RxPermissions(this@AddGrainActivity)

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

        btnAddImage.setOnClickListener {
            checkPermissions()
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

    fun checkPermissions(){
        val rxPermissions = RxPermissions(this)

        rxPermissions.request(
                Manifest.permission.CAMERA
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe {
            if (it) {
                //申请的权限全部允许
                Toast.makeText(this@AddGrainActivity, "允许了权限!", Toast.LENGTH_SHORT).show()
                selectImage()

            } else {
                //只要有一个权限被拒绝，就会执行
                Toast.makeText(this@AddGrainActivity, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun selectImage(){
        Matisse.from(this)
                .choose(MimeType.ofImage(), false)
                .capture(true) // 使用相机，和 captureStrategy 一起使用
                .captureStrategy(CaptureStrategy(true, "com.hlt.piccompresstest","test"))
                .theme(R.style.Matisse_Dracula)
                .maxSelectable(1)
                .addFilter(object : Filter() {
                    override fun constraintTypes(): Set<MimeType?>? {
                        return object : HashSet<MimeType?>() {
                            init {
                                add(MimeType.PNG)
                            }
                        }
                    }

                    override fun filter(context: Context?, item: Item): IncapableCause? {
                        try {
                            val inputStream: InputStream? = contentResolver.openInputStream(item.getContentUri())
                            val options = BitmapFactory.Options()
                            options.inJustDecodeBounds = true
                            BitmapFactory.decodeStream(inputStream, null, options)
                            val width = options.outWidth
                            val height = options.outHeight

//                            if (width >= 500)
//                                return new IncapableCause("宽度超过500px");
                        } catch (e: FileNotFoundException) {
                            e.printStackTrace()
                        }
                        return null
                    }
                }) //                .gridExpectedSize((int) getResources().getDimension(R.dimen.imageSelectDimen))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.87f)
                .imageEngine(GlideLoadEngine())
                .forResult(REQUEST_CODE_CHOOSE_PHOTO_ALBUM)
    }

    override fun getLayoutId() = R.layout.activity_add_grain

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0x201) {
            //选择单位回来
            try {
                val companyItemData = data?.getSerializableExtra("data") as CompanyItemData
                fbdw.text = companyItemData.dwmc
            } catch (e: Exception) {

            }

        } else if (requestCode == REQUEST_CODE_CHOOSE_PHOTO_ALBUM) {
            //选择图片回来
            //图片路径 同样视频地址也是这个 根据requestCode
            val pathList: List<Uri> = Matisse.obtainResult(data)
            for (_Uri in pathList) {
                Glide.with(this).load(_Uri).into(grain_image)
            }

        }

    }

    fun checkParams() {

    }

    companion object {
        val TAG = AddGrainActivity::class.simpleName
    }
}