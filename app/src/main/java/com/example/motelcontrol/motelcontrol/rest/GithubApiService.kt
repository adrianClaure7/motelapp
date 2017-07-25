package com.example.motelcontrol.motelcontrol.rest

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface GithubApiService {

    @GET("products")
    fun get(): Observable<MutableList<Product>>

    @POST("products")
    fun post(@Body product: Product): Call<MutableList<Product>>

    @PUT("products/{id}")
    fun update(@Path("id") id: Long, @Body book: Product): Call<MutableList<Product>>

    @DELETE("products/{id}")
    fun delete(@Path("id") id: Long): Call<MutableList<Product>>
    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): GithubApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://10.31.176.26:3000/")
                    .build()

            return retrofit.create(GithubApiService::class.java)
        }
    }

}
object ProductsRepositoryProvider {
    val apiService = GithubApiService.create()

    fun provideProductsRepository(): ProductsRepository {
        return ProductsRepository(apiService)
    }
}

class ProductsRepository(val apiService: GithubApiService) {

    fun get(): Observable<MutableList<Product>> {
        return apiService.get()
    }

    fun post(body: Product): Call<MutableList<Product>> {
        return apiService.post(body)
    }

    fun update(body: Product): Call<MutableList<Product>> {
        return apiService.update(body.id, body)
    }

    fun delete(id: Long): Call<MutableList<Product>> {
        return apiService.delete(id)
    }
}