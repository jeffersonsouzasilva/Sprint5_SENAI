package com.example.calculoimc;

import android.os.Bundle;
import android.widget.Button;
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
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    //COMECA AQUI O CODIGO
    resultado = findViewById(R.id.resultado_Calculo);

    Button botaoCalcular = findViewById(R.id.btn_Calcular);

    botaoCalcular.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calcular();

        }

        private void calcular(){

        }
    });

    }
}