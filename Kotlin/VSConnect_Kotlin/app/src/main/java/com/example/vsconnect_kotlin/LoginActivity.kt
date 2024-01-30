package com.example.vsconnect_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.vsconnect_kotlin.apis.EndpointInterface
import com.example.vsconnect_kotlin.apis.RetrofitConfig
import com.example.vsconnect_kotlin.databinding.ActivityLoginBinding
import com.example.vsconnect_kotlin.models.Login
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.StandardCharsets
import java.util.Base64

class LoginActivity : AppCompatActivity() {

    //E uma propriedade privada com o nome binding do tipo ActivityLoginBinding
    private lateinit var binding: ActivityLoginBinding

    private val clienteRetrofit = RetrofitConfig.obterInstanciaRetrofit()

    private val endpoints = clienteRetrofit.create(EndpointInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Atribui a variavel binding um objeto que contem referencias(propriedades) aos elementos definidos no layout

        binding = ActivityLoginBinding.inflate(layoutInflater) //alteracao a partir desse arquivo vai salva

        val sharedPreferences = getSharedPreferences("idUsuario",Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        editor.remove("idUsuario")

        editor.apply()

        binding.btnEntrar.setOnClickListener {
//            //variavel mainIntent com a intencao de sair da LoginActivity e ir para MainActivity
//            val mainIntent = Intent(this@LoginActivity,
//                MainActivity::class.java) //val e uma CONST do JS
//
//            //Executa a intecao armazenada na variavel mainIntent
//            startActivity(mainIntent)
//
//            //finaliza a LoginActivity
//            finish()

            autenticarUsuario()
        }
        setContentView(binding.root)//alteracao a partir desse arquivo vai salva
    }

    private fun autenticarUsuario(){
        val root: View = binding.root

        val  idEmail = root.findViewById<EditText>(R.id.campo_email)
        val  idSenha = root.findViewById<EditText>(R.id.campo_senha)

        val emailDigitado = idEmail.text.toString()
        val senhaDigitado = idSenha.text.toString()

        val usuario = Login(emailDigitado, senhaDigitado)

        endpoints.login(usuario).enqueue(object  : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response:
                Response<JsonObject>) {
                    when(response.code()){
                        200 -> {
                            val idUsuario = decodificarToken(response.body()
                                .toString())

                            val sharedPreferences = getSharedPreferences("idUsuario", Context.MODE_PRIVATE)

                            val editor = sharedPreferences.edit()

                            editor.putString("idUsuario", idUsuario.toString())

                            editor.apply()

                            //direcionando o uruario para tela de lista de servicos
                            val mainIntent = Intent(this@LoginActivity,MainActivity::class.java)

                            startActivity(mainIntent)

                            finish()
                        }
                        403 -> {tratarFalhaNaAutenticacao("E-mail e/ou senha inválidos.")}
                        else -> { tratarFalhaNaAutenticacao("Falha ao se logar!")}
                    }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                tratarFalhaNaAutenticacao("Falha ao tentar se logar!")
            }

        })
    }

    private fun tratarFalhaNaAutenticacao(mensagemErro: String){
        Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT).show()
    }

    private fun decodificarToken(token: String): Any {
        val partes = token.split(".")
        val payloadBase64 = partes[1]

        val payloadBytes = Base64.getUrlDecoder().decode(payloadBase64)
        val payloadJson = String(payloadBytes, StandardCharsets.UTF_8)

        val json = JSONObject(payloadJson)
        return json["idUsuario"].toString()
    }
}