package com.idisfkj.androidapianalysis.binder

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by idisfkj on 2/4/21.
 * Email: idisfkj@gmail.com.
 */
data class Goods(
        val id: Int,
        val name: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Goods> {
        override fun createFromParcel(parcel: Parcel): Goods {
            return Goods(parcel)
        }

        override fun newArray(size: Int): Array<Goods?> {
            return arrayOfNulls(size)
        }
    }
}