package modelo;

import java.util.Date;

public class Libros {
    
    private int idLibro;
    private String nombreLibro;
    private Date fecha;

    public Libros(int idLibro) {
        this.idLibro = idLibro;
    }

    public void setId(int id) {
        this.idLibro = idLibro;
    }

    public int getId() {
        return idLibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}