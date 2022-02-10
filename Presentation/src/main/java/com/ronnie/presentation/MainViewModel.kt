package com.ronnie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronnie.domain2.Product
import com.ronnie.domain2.Repository
import com.ronnie.presentation.utils.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _productResponse: MutableStateFlow<UIState<Product>> =
        MutableStateFlow(UIState.Loading)
    val productResponse = _productResponse.asStateFlow()

    fun getProduct() = viewModelScope.launch {
        _productResponse.value = UIState.Loading
        delay(3000)
        try {
            _productResponse.value = UIState.Success(repository.getData())
        } catch (_: Exception) {
            _productResponse.value = UIState.Error("There was an error loading data")
        }
    }
}