package com.example.motelcontrol.motelcontrol.rest

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Claure on 7/22/2017.
 */
class RestCalls() {
    private val _items = mutableListOf<Product>()
    var products = ProductsRepositoryProvider.provideProductsRepository()

    fun delete(id: Long) {
        val deleteProduct = products.delete(id)
        Log.d("Before delete", "executeeeee")
        deleteProduct.enqueue(object : Callback<MutableList<Product>> {
            override fun onResponse(call: Call<MutableList<Product>>, response: Response<MutableList<Product>>) {
                Log.d("Paso", response.body().toString())
            }
            override fun onFailure(call: Call<MutableList<Product>>, t: Throwable){
                Log.d("Error", t.toString())
            }
        })
    }

    fun update(product: Product) {
        val updateProduct = products.update(product)

        Log.d("Before update", "executeeeee")
        var a = updateProduct.enqueue(object : Callback<MutableList<Product>> {
            override fun onResponse(call: Call<MutableList<Product>>, response: Response<MutableList<Product>>) {
                Log.d("Paso", response.body().toString())
            }
            override fun onFailure(call: Call<MutableList<Product>>, t: Throwable){
                Log.d("Error", t.toString())
            }
        })
    }

    fun add(product: Product) {
        val addProduct = products.post(product)

        Log.d("Before Add", "executeeeee")
        var a = addProduct.enqueue(object : Callback<MutableList<Product>> {
            override fun onResponse(call: Call<MutableList<Product>>, response: Response<MutableList<Product>>) {
                Log.d("Paso", response.body().toString())
            }
            override fun onFailure(call: Call<MutableList<Product>>, t: Throwable){
                Log.d("Error", t.toString())
            }
        })
    }

    fun fillProducts() {
        products.get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
                    Log.d("Result", "${result[0].name}")
                    this._items.addAll(0, result)
                }, { error ->
                    Log.d("Result", "Error")
                    error.printStackTrace()
                })
    }
}