package com.joseoliva.losgoyapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.joseoliva.losgoyapp.databinding.ActivityMainBinding
import com.joseoliva.losgoyapp.ui.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewmodel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        initListeners()
        //viewmodel.sendRankingToFirebase()
        //viewmodel.sendPreguntasToFirebase()
    }

    private fun initUI() {
    }

    private fun initListeners() {
        binding.btnStart.setOnClickListener {
            gotoQuizz()
            //startRotationAnimation(binding.ivlogo)
            /*val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)*/
        }
    }

    private fun startRotationAnimation(view: View){
        //creo la animacion de la vista con unas propiedades determinadas
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction(gotoQuizz()) //lo que pasemos aqui se ejecuta cuando termine la animacion. En este caso recibe otra lambda
            start()
        }
    }

    private fun gotoQuizz(): Runnable? {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        return null
    }

    override fun onBackPressed() {
    }
}
