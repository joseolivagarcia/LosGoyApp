package com.joseoliva.losgoyapp.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
data class rankingModel(
    var nombre: String? = null,
    var puntos: Int? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeValue(puntos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<rankingModel> {
        override fun createFromParcel(parcel: Parcel): rankingModel {
            return rankingModel(parcel)
        }

        override fun newArray(size: Int): Array<rankingModel?> {
            return arrayOfNulls(size)
        }
    }
}
