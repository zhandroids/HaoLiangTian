package com.hlt.hao.grain.add

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hlt.hao.BaseActivity
import com.hlt.hao.R
import com.tbruyelle.rxpermissions3.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy
import com.zhihu.matisse.listener.OnCheckedListener
import com.zhihu.matisse.listener.OnSelectedListener
import kotlinx.android.synthetic.main.activity_upload_image.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.Companion.create
import okhttp3.MultipartBody.Part.Companion.createFormData

import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 *create by zhangzhuo
 *time: 2020/8/22
 *desc 添加订单图片页面
 */
class UploadImageActivity : BaseActivity() {

    val viewModel: AddGrainViewModel by viewModels()
    val REQUEST_CODE_CHOOSE_PHOTO_ALBUM = 0x101
    //订单id
    var refId = ""
    //表名
    val tab = "lsddfb"


    override fun initView() {

        refId = intent.getStringExtra("refId")

        imageAdd.setOnClickListener {
            checkPermissions()
        }
    }

    override fun getLayoutId() = R.layout.activity_upload_image

    private fun checkPermissions() {
        val rxPermissions = RxPermissions(this)

        rxPermissions.request(
                Manifest.permission.CAMERA
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe {
            if (it) {
                //申请的权限全部允许
                Toast.makeText(this@UploadImageActivity, "允许了权限!", Toast.LENGTH_SHORT).show()
                selectImage()

            } else {
                //只要有一个权限被拒绝，就会执行
                Toast.makeText(this@UploadImageActivity, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectImage() {
        Matisse.from(this@UploadImageActivity)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        CaptureStrategy(true, "com.hlt.hao.fileprovider", "test"))
                .maxSelectable(1)

                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(GlideEngine())
                .setOnSelectedListener(OnSelectedListener { uriList: List<Uri?>?, pathList: List<String?> -> Log.e("onSelected", "onSelected: pathList=$pathList") })
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(OnCheckedListener { isChecked: Boolean -> Log.e("isChecked", "onCheck: isChecked=$isChecked") })
                .forResult(REQUEST_CODE_CHOOSE_PHOTO_ALBUM)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE_PHOTO_ALBUM) {
            //选择图片回来
            //图片路径 同样视频地址也是这个 根据requestCode
            val uriList: List<Uri> = Matisse.obtainResult(data)
            val pathList = Matisse.obtainPathResult(data)
            uploadImages(pathList)
            for (_Uri in uriList) {
                Glide.with(this).load(_Uri).into(grainImage)
            }

        }

    }

    private fun uploadImages(pathList: MutableList<String>) {
        val files: MutableList<MultipartBody.Part> = ArrayList()

        for (imagePath in pathList) {

            //本地图片路径localMedia.get(i).getPath();
//            val path: String = localMedia.get(i).getPath()
            val file = File(imagePath)

            //上传
            val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val image: MultipartBody.Part = createFormData("picture", file.name, requestBody)

            files.add(image)
        }

//        refId = "133"

        viewModel.uploadImage(refId,tab,files).observe(this, Observer {
            if (it.isState){
                if (it.data.success) {
                    Toast.makeText(applicationContext,"上传成功",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext,"上传失败",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext,"上传失败",Toast.LENGTH_SHORT).show()

            }
        })
    }


}