package cl.inacap.misconciertos.dto;

public class Concierto {
    private String nombArtista;
    private String fecha;
    private String genMusical;
    private int valorEntrada;
    private int calificacion;

    public String getNombArtista() {
        return nombArtista;
    }

    public void setNombArtista(String nombArtista) {
        this.nombArtista = nombArtista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGenMusical() {
        return genMusical;
    }

    public void setGenMusical(String genMusical) {
        this.genMusical = genMusical;
    }

    public int getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(int valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
