package com.ronnie.domain2


interface Repository {
    suspend fun getData(): Product
}