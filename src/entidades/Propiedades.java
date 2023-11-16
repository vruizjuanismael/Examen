/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

public class Propiedades {
    private int id;
    private String nombre;
    private String direccion;
    private String caracteristicas;
    private boolean estado;
    private double precioAlquiler;

    public Propiedades() {
    }

    public Propiedades(int id, String nombre, String direccion, String caracteristicas, boolean estado, double precioAlquiler) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
        this.precioAlquiler = precioAlquiler;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }
    
    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", direccion=" + direccion + ", caracteristicas=" + caracteristicas + ", estado=" + estado + ", precioAlquiler=" + precioAlquiler;
    }
}
