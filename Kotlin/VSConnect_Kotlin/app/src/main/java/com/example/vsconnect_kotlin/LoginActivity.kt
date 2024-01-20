package com.example.vsconnect_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vsconnect_kotlin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    //E uma propriedade privada com o nome binding do tipo ActivityLoginBinding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Atribui a variavel binding um objeto que contem referencias(propriedades) aos elementos definidos no layout

        binding = ActivityLoginBinding.inflate(layoutInflater) //alteracao a partir desse arquivo vai salva

        binding.btnEntrar.setOnClickListener {
            //variavel mainIntent com a intencao de sair da LoginActivity e ir para MainActivity
            val mainIntent = Intent(this@LoginActivity,
                MainActivity::class.java) //val e uma CONST do JS

            //Executa a intecao armazenada na variavel mainIntent
            startActivity(mainIntent)

            //finaliza a LoginActivity
            finish()
        }
        setContentView(binding.root)//alteracao a partir desse arquivo vai salva
    }
}