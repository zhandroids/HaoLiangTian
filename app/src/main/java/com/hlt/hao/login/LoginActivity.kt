package com.hlt.hao.login

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hlt.hao.BaseActivity
import com.hlt.hao.HomeActivity
import com.hlt.hao.R
import com.hlt.hao.grain.list.GrainListActivity
import kotlinx.android.synthetic.main.activty_login.*

/**
 *create by zhangzhuo
 *time: 2020/7/12
 *desc
 */
class LoginActivity :BaseActivity() {

    private val viewModel : LoginViewModel by viewModels()

    override fun getLayoutId() = R.layout.activty_login

    override fun initView() {
        btnLogin.setOnClickListener {
            if (checkInputContent()){
                viewModel.login(
                        etUsername.text.toString(),
                        etPassword.text.toString(),
                        cbRememberPassword.isChecked.toString()
                ).observe(this@LoginActivity, Observer {

                    if (it.isState) {
                        if (it.data.success) {
                            Toast.makeText(applicationContext, "登录成功", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext,HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "登陆失败${it.data.message}", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(applicationContext, "登陆失败", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }



    }

    private fun checkInputContent():Boolean{
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (username.isEmpty()){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty()){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show()
            return false
        }

        return true

    }


}
