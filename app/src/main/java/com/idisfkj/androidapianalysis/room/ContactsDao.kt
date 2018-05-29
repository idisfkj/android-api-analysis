package com.idisfkj.androidapianalysis.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.idisfkj.androidapianalysis.ContactsModel

/**
 * Created by idisfkj on 2018/5/28.
 * Email : idisfkj@gmail.com.
 */
@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): List<ContactsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(contactsModel: ContactsModel)

    @Query("UPDATE contacts SET name = :name AND phone = :phone WHERE contacts_id = :id")
    fun updateContacts(id: Int, name: String, phone: String)

    @Query("DELETE FROM contacts WHERE contacts_id = :id")
    fun deleteContacts(id: Int)

}