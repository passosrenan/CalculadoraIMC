package br.senai.sp.cotia.imccalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ResultadoActivity extends AppCompatActivity {

    private ImageView imageView;
    private double imc=0;
    private String genero="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //pegando referencia o image view
        imageView = findViewById(R.id.image_view);

        //verificando se existe o imc na intent
        if (getIntent().hasExtra("imc")) {
            //com o extra vc independe do "if" pois se não houver ele define um valor default
            imc = getIntent().getDoubleExtra("imc", 0);
        }


        if (getIntent().hasExtra("genero")) {
            genero = getIntent().getStringExtra("genero");
        }

        if (genero.equals("masculino")) {
            if (imc <= 21) {
                imageView.setImageResource(R.drawable.magro);
            } else if (imc <= 26) {
                imageView.setImageResource(R.drawable.brad);
            } else {
                imageView.setImageResource(R.drawable.josoares);
            }
        }else{
                if (imc <= 21) {
                    imageView.setImageResource(R.drawable.agenlina);
                } else if (imc <= 26) {
                    imageView.setImageResource(R.drawable.thais);
                } else {
                    imageView.setImageResource(R.drawable.olivia);
                }
            }
        }
    }