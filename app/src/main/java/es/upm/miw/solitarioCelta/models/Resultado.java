package es.upm.miw.solitarioCelta.models;

public class Resultado {
    private String nombre;
    private int numFichas;
    private String hora;
    private String fecha;

    public Resultado(String nombre, int numFichas, String hora, String fecha) {
        this.nombre = nombre;
        this.numFichas = numFichas;
        this.hora = hora;
        this.fecha = fecha;
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

    @Override
    public String toString() {
        return "Resultado{" +
                "nombre='" + nombre + '\'' +
                ", numFichas=" + numFichas +
                ", hora='" + hora + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}

