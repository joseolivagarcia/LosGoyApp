package com.joseoliva.losgoyapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.joseoliva.losgoyapp.data.models.QuestionResponse
import com.joseoliva.losgoyapp.databinding.ActivitySplashBinding
import com.joseoliva.losgoyapp.ui.game.PreguntasActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashActivityViewModel by viewModels()
    private lateinit var binding: ActivitySplashBinding
    var lista: MutableList<QuestionResponse> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splashViewModel.dataFromFirebase()
        initUIState()

    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                splashViewModel.isLoading.collect{
                    binding.pbloading.isVisible = it
                    if (!it){
                        lista = splashViewModel.listado
                        navigateToGame()
                        Log.i("preguntas","accedo a game y la lista tiene: ${lista.size}")
                    }
                }
            }
        }
    }

    private fun navigateToHome() {
        //startActivity(Intent(this, MainActivity::class.java))
    }

    private fun navigateToGame() {
        //startActivity(Intent(this, PreguntasActivity::class.java))
        val intent = Intent(this, PreguntasActivity::class.java)
        intent.putParcelableArrayListExtra("preguntas", lista as ArrayList<out Parcelable>)
        startActivity(intent)
    }

}