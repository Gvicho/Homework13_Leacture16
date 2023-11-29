package com.example.homework13_leacture16

import android.os.Parcel
import android.os.Parcelable

data class Field(
    val field_id: Int,
    val hint: String,
    val field_type: String,
    val keyboard: String,
    val required: Boolean,
    val is_active: Boolean,
    val icon: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(field_id)
        parcel.writeString(hint)
        parcel.writeString(field_type)
        parcel.writeString(keyboard)
        parcel.writeByte(if (required) 1 else 0)
        parcel.writeByte(if (is_active) 1 else 0)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Field> {
        override fun createFromParcel(parcel: Parcel): Field {
            return Field(parcel)
        }

        override fun newArray(size: Int): Array<Field?> {
            return arrayOfNulls(size)
        }
    }
}

data class FieldStack(val id:Int,
                      val list: List<Field>
)


typealias stackOfFieldStack = List<FieldStack>

data class FieldsWrapper(val stacks: List<FieldStack>)
