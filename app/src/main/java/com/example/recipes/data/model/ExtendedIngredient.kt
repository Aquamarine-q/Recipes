package com.example.recipes.data.model

import android.os.Parcel
import android.os.Parcelable

data class ExtendedIngredient(
    val id: Int,
    val aisle: String?,
    val image: String?,
    val consistency: String?,
    val name: String?,
    val nameClean: String?,
    val original: String?,
    val originalName: String?,
    val amount: Double,
    val unit: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(aisle)
        parcel.writeString(image)
        parcel.writeString(consistency)
        parcel.writeString(name)
        parcel.writeString(nameClean)
        parcel.writeString(original)
        parcel.writeString(originalName)
        parcel.writeDouble(amount)
        parcel.writeString(unit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExtendedIngredient> {
        override fun createFromParcel(parcel: Parcel): ExtendedIngredient {
            return ExtendedIngredient(parcel)
        }

        override fun newArray(size: Int): Array<ExtendedIngredient?> {
            return arrayOfNulls(size)
        }
    }
}