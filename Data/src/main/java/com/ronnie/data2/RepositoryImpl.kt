package com.ronnie.data2

import com.ronnie.domain2.Product
import com.ronnie.domain2.Repository
import kotlin.random.Random

class RepositoryImpl : Repository {

    private val productList = listOf(
      Product("Samsung phone", 1),
        Product("Apple Fruit", 1),
        Product("Macbook pro", 1),
        Product("Fridge", 1),
        Product("Soda", 1),
        Product("Shoes", 1)
    )

    @Throws(Exception::class)
    override suspend fun getData(): Product {
        val showData = Random.nextBoolean()
        if (showData) {
            return productList.random()
        } else {
            throw Exception()
        }
    }

    companion object {
        private var instance: RepositoryImpl? = null
        fun getInstance() = instance ?: RepositoryImpl().also {
            instance = it
        }
    }
}