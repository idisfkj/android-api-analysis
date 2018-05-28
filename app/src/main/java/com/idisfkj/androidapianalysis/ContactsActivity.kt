package com.idisfkj.androidapianalysis

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.idisfkj.androidapianalysis.viewmodel.ContactsFactory
import com.idisfkj.androidapianalysis.viewmodel.ContactsViewModel
import kotlinx.android.synthetic.main.activity_contacts_layout.*

@Suppress("UNCHECKED_CAST")
class ContactsActivity : AppCompatActivity() {

    private lateinit var mViewModel: ContactsViewModel
    private var mTestViewModel: ContactsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_layout)
        setupViewModel()
        setupRecyclerView()
//        lifecycle.addObserver(MyLifeCycleObserver(lifecycle))
    }

    override fun onStart() {
        super.onStart()
        mViewModel.start()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ContactsAdapter(this)
    }

    private fun setupViewModel() {
        if (mTestViewModel == null) {
//            Log.d("idisfkj", "-----textViewModel init------")
            mTestViewModel = ContactsViewModel(this.application)
        }

        mViewModel = ViewModelProviders.of(this, ContactsFactory.getInstance(application))[ContactsViewModel::class.java]
//        mViewModel = ViewModelProviders.of(this)[ContactsViewModel::class.java]

//        Log.d("idisfkj", "testViewModel: " + mTestViewModel.toString())
//        Log.d("idisfkj", "normalViewModel: " + mViewModel.toString())

        //active STARTED、RESUMED
        mViewModel.contactsList.observe(this,
                Observer {
                    it?.let {
                        //                        Log.d("idisfkj", "update")
                        (recyclerView.adapter as ContactsAdapter).addAll(it)
                    }
                })
        mViewModel.title.observe(this,
                Observer { title = it })
        mViewModel.message.observe(this,
                Observer { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() })
    }

}
