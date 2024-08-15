package com.example.quiz_1_4.classes

import android.os.Parcel
import android.os.Parcelable

data class Block(
    val blockName: String,
    val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(blockName)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Block> {
        override fun createFromParcel(parcel: Parcel): Block {
            return Block(parcel)
        }

        override fun newArray(size: Int): Array<Block?> {
            return arrayOfNulls(size)
        }
    }
}
