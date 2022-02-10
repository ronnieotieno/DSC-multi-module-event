package com.ronnie.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.ronnie.presentation.databinding.ActivityMainBinding
import com.ronnie.presentation.utils.UIState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels{ MainViewModelFactory() }

    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        viewModel.getProduct()

        mainBinding.btnData.setOnClickListener {
            viewModel.getProduct()
        }

        lifecycleScope.launch {
            viewModel.productResponse.collect { state ->
                mainBinding.loader.isVisible = state is UIState.Loading

                when(state) {
                    is UIState.Loading -> {
                     //
                    }
                    is UIState.Success -> {
                       showData(state.value.name)
                    }
                    is UIState.Error -> {
                      showData(state.errorMessage ?: "There was an error")
                    }
                }
            }
        }
    }

    private fun showData(message:String) {
      mainBinding.dataText.text = message
    }
}