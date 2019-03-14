package apptablet.sacooliveros.edu.pe.swipedelete;

public class Coche {

    private int img;
    private String nombre;

    public Coche(int img, String nombre) {
        this.img = img;
        this.nombre = nombre;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
