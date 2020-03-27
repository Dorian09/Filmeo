package com.example.filmeo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private RadioButton rb1;
    private RadioButton rb2;
    private Spinner spinner1;
    private Button buttonGuardar;
    private Button btn2;
    ArrayList<Pelicula> Peliculas;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.txt_nombre);
        et2 = (EditText)findViewById(R.id.txt_director);
        rb1 = (RadioButton)findViewById(R.id.radioButton);
        rb2 = (RadioButton)findViewById(R.id.radioButton4);
        buttonGuardar = findViewById(R.id.button_guardar);
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        spinner1 = (Spinner)findViewById(R.id.spinner);
        Peliculas = new ArrayList<>();

        String [] opciones = {"Accion", "Suspenso", "Drama", "Comedia"};

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);

        spinner1.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mayuscula:
                Toast.makeText(this,"Mayuscula", Toast.LENGTH_LONG).show();
                break;
            case R.id.item_listado:
                enviar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void guardar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("¿Desea guardar la pelicula?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String s;
                        if (rb1.isChecked()==true){
                            s = ("ingles");
                            Peliculas.add(new Pelicula(et1.getText().toString(),et2.getText().toString(),spinner1.getSelectedItem().toString(),s));
                        } else if (rb2.isChecked()==true){
                            s = ("Español");
                            Peliculas.add(new Pelicula(et1.getText().toString(),et2.getText().toString(),spinner1.getSelectedItem().toString(),s));
                        }else{
                            Toast.makeText(getApplicationContext()," Pelicula guardada", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext()," Cancelado", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alerta = builder.create();
        alerta.show();
    }

    public void enviar(){
        if (Peliculas.size() > 0){
            Intent siguiente = new Intent(this, ListadoActivity.class);
            siguiente.putParcelableArrayListExtra("peliculas",Peliculas);
            startActivity(siguiente);
        }else{
            Toast.makeText(getApplicationContext(),"Agregue elementos", Toast.LENGTH_LONG).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
