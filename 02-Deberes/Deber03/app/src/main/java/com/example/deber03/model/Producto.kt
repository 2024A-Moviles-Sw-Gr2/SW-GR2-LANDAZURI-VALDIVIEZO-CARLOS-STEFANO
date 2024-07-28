package com.example.deber03.model

import androidx.annotation.DrawableRes

class Producto(
    val precioDescuento: Double,
    val precio: Double,
    @DrawableRes val imageRes: Int
) {

}