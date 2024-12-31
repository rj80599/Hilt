package com.example.mvvmhilt.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmhilt.model.ProductDataBase
import com.example.mvvmhilt.retrofit.ApiInterface
import com.example.mvvmhilt.model.Product
import com.example.mvvmhilt.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val productDataBase: ProductDataBase
) {

    private val _productList = MutableLiveData<Response<List<Product>>>(Response.Loading())

    val product: LiveData<Response<List<Product>>>
        get() = _productList

    suspend fun getProducts() {
        _productList.postValue(Response.Loading()) // Explicitly set loading state
        try {
            val result = apiInterface.getProducts()
            if (result.isSuccessful && result.body() != null) {
                withContext(Dispatchers.IO) {
                    productDataBase.getProductDao().addData(result.body()!!)
                }
                _productList.postValue(Response.Success(result.body()!!))
            } else {
                _productList.postValue(Response.Error(result.message()))
            }
        } catch (e: Exception) {
            _productList.postValue(Response.Error(e.message ?: "Unknown Error"))
        }
    }

//        // Fetch data from the local database
//        val localData = withContext(Dispatchers.IO) {
//            productDataBase.getProductDao().getData()
//        }
//        // Update LiveData with the data fetched from the local database
//        _productList.postValue(localData)
//    }
}