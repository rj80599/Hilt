package com.example.mvvmhilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmhilt.model.Product
import com.example.mvvmhilt.model.Response
import com.example.mvvmhilt.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

    val productLiveData: LiveData<Response<List<Product>>>
        get() = repository.product

    init {
        viewModelScope.launch {
            repository.getProducts()
        }
    }
}