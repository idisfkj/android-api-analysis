package com.idisfkj.androidapianalysis.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.idisfkj.androidapianalysis.R
import kotlinx.android.synthetic.main.activity_contacts_detail_layout.*

/**
 * Created by idisfkj on 2018/5/29.
 * Email : idisfkj@gmail.com.
 */
class ContactsDetailActivity : AppCompatActivity() {

    private var mContactsId: Int = 1
    private lateinit var mViewModel: ContactsDetailViewModel

    companion object {
        val EXTRA_CONTACTS_ID = "contacts_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_detail_layout)
        getExtra()
        setupViewModel()
    }

    private fun getExtra() {
        mContactsId = intent.getIntExtra(EXTRA_CONTACTS_ID, 1)
    }

    private fun setupViewModel() {
        mViewModel = ViewModelProviders.of(this)[ContactsDetailViewModel::class.java]
        mViewModel.getContactsById(mContactsId).observe(this, Observer {
            name.setText(it?.name)
            phone.setText(it?.phone)
        })
    }
}