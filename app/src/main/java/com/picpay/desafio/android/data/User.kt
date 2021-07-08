package com.picpay.desafio.android.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Parcelable