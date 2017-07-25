package com.example.motelcontrol.motelcontrol.rest

/**
 * Created by Claure on 7/19/2017.
 */

data class Product (
        val id: Long,
        val name: String,
        val category: MutableList<String>)