/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bantrabalhofinal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author udesc
 */
public class Album {
    private final int id;
    private String nome;
    private Date dataLancamento;
    private float precoDisco;
    private List<Musica> musicas;
    private Publicador publicador;

    public Album(int id) {
        this.id = id;
        this.musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public float getPrecoDisco() {
        return precoDisco;
    }

    public void setPrecoDisco(float precoDisco) {
        this.precoDisco = precoDisco;
    }
    
    public void addMusica(Musica musica){
        if (!this.musicas.contains(musica))
            this.musicas.add(musica);
    }
    
    public void removeMusica(Musica musica){
        this.musicas.remove(musica);
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public int getId() {
        return id;
    }

    public Publicador getPublicador() {
        return publicador;
    }

    public void setPublicador(Publicador publicador) {
        this.publicador = publicador;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", nome=" + nome + ", dataLancamento=" + dataLancamento + ", precoDisco=" + precoDisco + ", musicas=" + musicas + ", publicador=" + publicador + '}';
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
        final Album other = (Album) obj;
        return this.id == other.id;
    }
}
