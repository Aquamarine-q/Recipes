package com.example.recipes.data.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable

data class AnalyzedInstruction(
    val name: String?,
    val steps: List<Step>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        listOf<Step>().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                parcel.readParcelableList(this, Step::class.java.classLoader)
            }
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeParcelableList(steps,0)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnalyzedInstruction> {
        override fun createFromParcel(parcel: Parcel): AnalyzedInstruction {
            return AnalyzedInstruction(parcel)
        }

        override fun newArray(size: Int): Array<AnalyzedInstruction?> {
            return arrayOfNulls(size)
        }
    }
}