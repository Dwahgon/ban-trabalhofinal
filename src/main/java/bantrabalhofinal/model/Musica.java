/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bantrabalhofinal.model;

/**
 *
 * @author udesc
 */
public class Musica {
    private final int id;
    private String titulo;
    private int qntVistos;
    private String letra;
    private int idPublicador;

    public Musica(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQntVistos() {
        return qntVistos;
    }

    public void setQntVistos(int qntVistos) {
        this.qntVistos = qntVistos;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getIdPublicador() {
        return idPublicador;
    }

    public void setIdPublicador(int idPublicador) {
        this.idPublicador = idPublicador;
    }

    @Override
    public String toString() {
        return "Musica{" + "id=" + id + ", titulo=" + titulo + ", qntVistos=" + qntVistos + ", letra=" + letra + ", idPublicador=" + idPublicador + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Musica other = (Musica) obj;
        return this.id == other.id;
    }
}
