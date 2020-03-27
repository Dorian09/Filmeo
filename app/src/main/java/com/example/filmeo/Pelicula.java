package com.example.filmeo;

import android.os.Parcel;
import android.os.Parcelable;

public class Pelicula implements Parcelable {

    private String nombre;
    private String director;
    private String idioma;
    private String genero;

    public Pelicula(String nombre, String adirector, String idioma, String genero) {
        this.nombre = nombre;
        this.director = adirector;
        this.idioma = idioma;
        this.genero = genero;
    }

    protected Pelicula(Parcel in) {
        nombre = in.readString();
        director = in.readString();
        idioma = in.readString();
        genero = in.readString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAdirector() {
        return director;
    }

    public void setAdirector(String adirector) {
        this.director = adirector;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "nombre='" + nombre + '\'' +
                ", adirector='" + director + '\'' +
                ", idioma='" + idioma + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.director);
        dest.writeString(this.idioma);
        dest.writeString(this.genero);
    }

    public static final Creator<Pelicula> CREATOR = new Creator<Pelicula>() {
        @Override
        public Pelicula createFromParcel(Parcel in) {
            return new Pelicula(in);
        }

        @Override
        public Pelicula[] newArray(int size) {
            return new Pelicula[size];
        }
    };
}
