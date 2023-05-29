package id.capstone.wawasan.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dummy(
    val sales: String,
    val product: String,
    val stock: String,
    val recStock: String
) : Parcelable