package com.example.filmeo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListadoActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Pelicula> Peliculas =new ArrayList<Pelicula>();
    ArrayAdapter<Pelicula> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lv = (ListView)findViewById(R.id.listado);

        Intent in = getIntent();
        Peliculas = in.getParcelableArrayListExtra("peliculas");

        arrayAdapter = new ArrayAdapter<Pelicula>(this, R.layout.item_spinner, R.id.txt_spinner, Peliculas);
        //  arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, personas);
        lv.setAdapter(arrayAdapter);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Ordenar_genero:
                if (Peliculas.size() > 0){
                    Collections.sort(Peliculas,new Comparator<Pelicula>() {
                        @Override
                        public int compare(Pelicula o1, Pelicula o2) {
                            return o1.getGenero().compareTo(o2.getGenero());
                        }
                    });
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this,"Error: No hay elementos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ordenar_nombre:
                if (Peliculas.size() > 0){
                    Collections.sort(Peliculas,new Comparator<Pelicula>() {
                        @Override
                        public int compare(Pelicula o1, Pelicula o2) {
                            return o1.getNombre().compareTo(o2.getNombre());
                        }
                    });
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this,"Error: No hay elementos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.invertir_lista:
                if (Peliculas.size() > 0){
                    Collections.reverse(Peliculas);
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this,"Error: No hay elementos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.eliminar:
                if (Peliculas.size() > 0){
                    Peliculas.remove( 0);
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this,"No hay elementos que eliminar", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;
    }
}
