package com.app.zaigoinfotech.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.app.zaigoinfotech.R
import com.app.zaigoinfotech.databinding.ActivityLoginScreenBinding
import com.app.zaigoinfotech.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginScreen : AppCompatActivity() {
    private lateinit var activityLoginScreenBinding: ActivityLoginScreenBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginScreenBinding =
            DataBindingUtil.setContentView<ActivityLoginScreenBinding>(
                this,
                R.layout.activity_login_screen
            )
        activityLoginScreenBinding.viewModel = viewModel
        activityLoginScreenBinding.activity = this
        activityLoginScreenBinding.lifecycleOwner = this
        setupObservers()

    }

    private fun setupObservers() {
        //Show Error Dialog
        viewModel.showMessage.observe(this, Observer { message ->
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                    activityLoginScreenBinding.emailidTiet.setText("")
                    activityLoginScreenBinding.passwordTiet.setText("")
                })

            val alert: AlertDialog = builder.create()
            alert.setTitle("Message")
            alert.show()
        })

        //Show Email Validation Error
        viewModel.isEmailAddressError.observe(this, Observer { isError ->
            activityLoginScreenBinding.emailTil.error = resources.getString(R.string.erroremail)
            activityLoginScreenBinding.emailTil.isErrorEnabled = isError
        })

        //Show Password Validation Error
        viewModel.isPasswordError.observe(this, Observer { isError ->
            activityLoginScreenBinding.passwordTil.error =
                resources.getString(R.string.errorpassword)
            activityLoginScreenBinding.passwordTil.isErrorEnabled = isError
        })
    }

    fun loginUser(view: View) {
        viewModel.loginUser(
            activityLoginScreenBinding.emailidTiet.text.toString(),
            activityLoginScreenBinding.passwordTiet.text.toString()
        ).observe(this, Observer { userData ->
            if (userData != null) {
                startActivity(Intent(this, Dashboard::class.java))
                finish()
            }
        })
    }


}