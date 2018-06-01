package com.idisfkj.androidapianalysis.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.idisfkj.androidapianalysis.ContactsModel
import com.idisfkj.androidapianalysis.room.ContactsDataBase

/**
 * Created by idisfkj on 2018/5/29.
 * Email : idisfkj@gmail.com.
 */
class ContactsDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val mContactsDao by lazy { ContactsDataBase.getInstance(application).contactsDao() }

    fun getContactsById(id: Int): LiveData<ContactsModel> = mContactsDao.getContactsById(id)

}