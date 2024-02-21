package com.example.calculoimc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.calculoimc.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private TextView resultado;
    private TextView classificar;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    //COMECA AQUI O CODIGO
    resultado = findViewById(R.id.resultado_Calculo);
    classificar = findViewById(R.id.classificacao);

    Button botaoCalcular = findViewById(R.id.btn_Calcular);

    botaoCalcular.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calcular();

        }

        private void calcular(){
            EditText altura = findViewById(R.id.input_Altura);
            EditText peso = findViewById(R.id.input_Peso);

            double alturaEscolhida = Double.parseDouble(altura.getText().toString());
            double pesoEscolhido = Double.parseDouble(peso.getText().toString());

            double imc = pesoEscolhido / (alturaEscolhida * alturaEscolhida);
            double imcCalculado = 0.0;

            String imcResultado;

            if (imc < 18.5) {
                imcCalculado = imc;
                imcResultado = "Abaixo do peso";
            } else if (imc >= 18.5 && imc < 25) {
                imcCalculado = imc;
                imcResultado = "Peso normal";
            } else if (imc >= 25 && imc < 30) {
                imcCalculado = imc;
                imcResultado = "Sobrepeso";
            } else {
                imcCalculado = imc;
                imcResultado = "Obesidade";
            }

            resultado.setText((imcResultado));
            resultado.setVisibility(View.VISIBLE);
            classificar.setText(String.valueOf(imcCalculado));
            classificar.setVisibility(View.VISIBLE);


        }
    });

    }
}