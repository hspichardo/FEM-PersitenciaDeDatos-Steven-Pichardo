package es.upm.miw.solitarioCelta.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Resultado implements Parcelable {
    private int id;
    private String nombre;
    private int numFichas;
    private String hora;
    private String fecha;
    private String tiempoJugado;

    public Resultado(int id, String nombre, int numFichas, String hora, String fecha, String tiempoJugado) {
        this.id = id;
        this.nombre = nombre;
        this.numFichas = numFichas;
        this.hora = hora;
        this.fecha = fecha;
        this.tiempoJugado = tiempoJugado;
    }

    public String  getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(String  tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumFichas() {
        return numFichas;
    }

    public void setNumFichas(int numFichas) {
        this.numFichas = numFichas;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    protected Resultado (Parcel in){
        id = in.readInt();
        nombre = in.readString();
        fecha = in.readString();
        hora = in.readString();
        numFichas = in.readInt();
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "nombre='" + nombre + '\'' +
                ", numFichas=" + numFichas +
                ", hora='" + hora + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(nombre);
            parcel.writeString(fecha);
            parcel.writeString(hora);
            parcel.writeInt(numFichas);
            parcel.writeString(tiempoJugado);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Resultado> CREATOR = new Parcelable.Creator<Resultado>() {
        @Override
        public Resultado createFromParcel(Parcel in) {
            return new Resultado(in);
        }

       @Override
        public Resultado[] newArray(int size){
            return new Resultado[size];
       }
    };
}

