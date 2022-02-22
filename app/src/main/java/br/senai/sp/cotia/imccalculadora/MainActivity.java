package br.senai.sp.cotia.imccalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText editPeso, editAltura;
    private Button btCalcular, btLimpar;
    private TextView resultado, classificar;
    private RadioGroup groupGenero;
    private Spinner spinerGenero;
    private String genero;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //populando variáveis com os ids
        spinerGenero = findViewById(R.id.spinner_genero);
        groupGenero = findViewById(R.id.group_genero);
         editPeso = findViewById(R.id.edit_peso);
        editAltura = findViewById(R.id.edit_altura);
        btCalcular = findViewById(R.id.btCalcular);
        btLimpar = findViewById(R.id.btLimpar);
        classificar = findViewById(R.id.classificar);
        resultado = findViewById(R.id.result);


        //associando  a variavel a uma classe anonima que sera chamado
        btCalcular.setOnClickListener(v ->{
            //
            if(editPeso.getText().toString().isEmpty()){
                editPeso.setError(getString(R.string.valida_peso));
                Toast.makeText(getBaseContext(),R.string.valida_peso, Toast.LENGTH_LONG).show();
            }else if(editAltura.getText().toString().isEmpty()){
                editAltura.setError(getString(R.string.valida_altura));
                Snackbar.make(editAltura, R.string.valida_altura, Snackbar.LENGTH_SHORT).show();
            }else {
                double peso, altura, imc;
                peso = Double.parseDouble(editPeso.getText().toString());
                altura = Double.parseDouble(editAltura.getText().toString());
                imc = peso / (altura * altura);

                if (imc < 16) {
                    classificar.setText(R.string.abaixo);
                    classificar.setBackgroundColor(getResources().getColor(R.color.amarelo));

                } else if (imc < 24.9) {
                    classificar.setText(R.string.saudavel);
                    classificar.setBackgroundColor(getResources().getColor(R.color.verde));

                } else if (imc < 34.9) {
                    classificar.setText(R.string.sobrepeso);
                    classificar.setBackgroundColor(getResources().getColor(R.color.verde));

                } else {
                    classificar.setText(R.string.obesidade);
                    classificar.setBackgroundColor(getResources().getColor(R.color.red));


                }
                resultado.setText(getString(R.string.resultado, imc));

                //pegar o valor selecionado na sppiner
                if (spinerGenero.getSelectedItemPosition() == 0) {
                    genero = "masculino";

                } else {
                    genero = "feminino";
                }
                switch (groupGenero.getCheckedRadioButtonId()){
                    case R.id.radioFeminino:
                        genero = "masculino";
                        break;
                    case R.id.radioMasculino:
                        genero = "feminino";
                        break;


                }
                //abrir activityResultado
                Intent intent = new Intent(this, ResultadoActivity.class);
                //pendurando informações na intent
                intent.putExtra("imc", imc);
                intent.putExtra("genero", genero);
                startActivity(intent);
                

            }


        });
        btLimpar.setOnClickListener(v ->{
            classificar.setText("");
            resultado.setText("");
            editPeso.getText().clear();
            editAltura.getText().clear();
            classificar.setBackgroundColor(getResources().getColor(R.color.white));
            editPeso.requestFocus();
        });
    }
}