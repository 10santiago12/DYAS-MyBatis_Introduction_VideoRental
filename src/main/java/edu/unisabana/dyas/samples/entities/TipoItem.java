package edu.unisabana.dyas.samples.entities;

import java.io.Serializable;

public class TipoItem implements Serializable {
    private int id;
    private String descripcion;

    public TipoItem(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public TipoItem() {}

    // ✅ Usar nombres JavaBeans estándar: getId()/setId()
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoItem{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
}