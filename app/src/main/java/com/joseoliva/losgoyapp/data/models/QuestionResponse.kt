package com.joseoliva.losgoyapp.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
data class QuestionResponse(
    val pregunta: String? = null,
    val respuestaA: String? = null,
    val respuestaB: String? = null,
    val respuestaC: String? = null,
    val respuestaD: String? = null,
    val respuestaCorrecta: String? = null

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pregunta)
        parcel.writeString(respuestaA)
        parcel.writeString(respuestaB)
        parcel.writeString(respuestaC)
        parcel.writeString(respuestaD)
        parcel.writeString(respuestaCorrecta)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionResponse> {
        override fun createFromParcel(parcel: Parcel): QuestionResponse {
            return QuestionResponse(parcel)
        }

        override fun newArray(size: Int): Array<QuestionResponse?> {
            return arrayOfNulls(size)
        }
    }
}
