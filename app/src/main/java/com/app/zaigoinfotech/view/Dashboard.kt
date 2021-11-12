package com.app.zaigoinfotech.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zaigoinfotech.R
import com.app.zaigoinfotech.databinding.ActivityDashboardBinding
import com.app.zaigoinfotech.model.DataLogin
import com.app.zaigoinfotech.view.adapter.ListAdapter
import com.app.zaigoinfotech.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Dashboard : AppCompatActivity() {
    private lateinit var activityDashboardBinding: ActivityDashboardBinding
    val viewModel: MainViewModel by viewModels()
    private lateinit var userDetails: DataLogin


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDashboardBinding = DataBindingUtil.setContentView<ActivityDashboardBinding>(
            this,
            R.layout.activity_dashboard
        );
        activityDashboardBinding.viewModel = viewModel
        activityDashboardBinding.lifecycleOwner = this



        viewModel.getUser()

        viewModel.userDetails.observe(this, Observer { userDetailsResult ->
            userDetails = userDetailsResult
        })
        viewModel.getSweepstackesList().observe(this, Observer { listData ->
            val adapter: ListAdapter = ListAdapter(this, listData)
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            activityDashboardBinding.sweepListRv.layoutManager = layoutManager
            activityDashboardBinding.sweepListRv.adapter = adapter
        })
    }

    fun openAddAddress(view: android.view.View) {
        startActivity(Intent(this, AddressActivity::class.java))
    }

    fun openCamera(view: android.view.View) {
        var intent = Intent(this, ImageAdd::class.java)
        intent.putExtra("id", userDetails.user_details.id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menuhome, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
               showDialogConformation()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialogConformation() {
        AlertDialog.Builder(this)
            .setTitle("Logout").setMessage("Are you sure you want to logout ?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                viewModel.logout()
                startActivity(Intent(this, SplashScreen::class.java))
                finish()
            }).setNegativeButton("No", null).show()
    }
}