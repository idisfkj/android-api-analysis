package com.idisfkj.androidapianalysis.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * Created by idisfkj on 2018/5/28.
 * Email : idisfkj@gmail.com.
 */
@Suppress("UNCHECKED_CAST")
class ContactsFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: ContactsFactory? = null

        fun getInstance(application: Application): ContactsFactory {
            if (instance == null) {
                instance = ContactsFactory(application)
            }
            return instance as ContactsFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            return ContactsViewModel(application, "Factory Contacts") as T
        }
        return super.create(modelClass)
    }
}