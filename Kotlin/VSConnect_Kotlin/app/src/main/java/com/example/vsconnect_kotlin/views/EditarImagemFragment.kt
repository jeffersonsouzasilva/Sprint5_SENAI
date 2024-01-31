package com.example.vsconnect_kotlin.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.vsconnect_kotlin.R
import com.example.vsconnect_kotlin.apis.EndpointInterface
import com.example.vsconnect_kotlin.apis.RetrofitConfig
import com.example.vsconnect_kotlin.databinding.FragmentEditarImagemBinding
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class EditarImagemFragment : Fragment() {

    private var _binding: FragmentEditarImagemBinding? = null

    private val binding get() = _binding!!

    private val clienteRetrofit = RetrofitConfig.obterInstanciaRetrofit()

    private val  endpoints = clienteRetrofit.create(EndpointInterface::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditarImagemBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sharedPreferences = requireContext().getSharedPreferences("idUsuario",Context.MODE_PRIVATE)

        val  idUsuario = sharedPreferences.getString("idUsuario","")

        buscarUsuarioPorID(idUsuario.toString())

        return root
    }

    private  fun  buscarUsuarioPorID(idUsuario: String){
        endpoints.buscarUsuarioPorID(UUID.fromString(idUsuario)).enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val root: View = binding.root

                val  viewImagemPerfil = root.findViewById<ImageView>(R.id.id_view_imagem_perfil)

                val imagemPerfilUsuario = JSONObject(response.body().toString()).getString("url_img")

                val urlImagem = "http://172.16.27.108:8099/img/" + imagemPerfilUsuario

                //Usar Picasso para carregar e exibir a imagem na ImageView
                Picasso.get().load(urlImagem).into(viewImagemPerfil)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private  fun buscarUsuarioPorID(idUsuario: String) {...}

    private fun mostrarOpcoesEscolhaImagem(){
        val escolherImagemIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}