package com.features.users.domain.model

import android.os.Parcel
import android.os.Parcelable

data class UserDomainModel(
    val username          : String,
    val avatarUrl         : String,
    val type              : String,
    val isFavorite        : Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(avatarUrl)
        parcel.writeString(type)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDomainModel> {
        override fun createFromParcel(parcel: Parcel): UserDomainModel {
            return UserDomainModel(parcel)
        }

        override fun newArray(size: Int): Array<UserDomainModel?> {
            return arrayOfNulls(size)
        }
    }

}