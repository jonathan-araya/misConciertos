package cl.inacap.misconciertos;

public class Modelo {


    private String fecha1;
    private String nombre;
    private int valorDeEntrada;
    private int image;

    public Modelo(String fecha1, String nombre, int valorDeEntrada, int image) {
        this.fecha1 = fecha1;
        this.nombre = nombre;
        this.valorDeEntrada = valorDeEntrada;
        this.image = image;
    }

    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValorDeEntrada() {
        return valorDeEntrada;
    }

    public void setValorDeEntrada(int valorDeEntrada) {
        this.valorDeEntrada = valorDeEntrada;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



}
