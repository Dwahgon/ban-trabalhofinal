/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bantrabalhofinal.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author udesc
 */
public class Album {

    private final int id;
    private String nome;
    private Date dataLancamento;
    private float precoDisco;
    private int idPublicador;
    private SortedMap<Integer, Musica> musicas;

    public Album(int id) {
        this.id = id;
        musicas = new TreeMap<>();
    }

    public Album(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt("id"));
        this.nome = resultSet.getString("nome");
        this.dataLancamento = resultSet.getDate("datalancamento");
        this.precoDisco = resultSet.getFloat("precodisco");
        this.idPublicador = resultSet.getInt("idpublicador");
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

    public int getId() {
        return id;
    }

    public int getIdPublicador() {
        return idPublicador;
    }

    public void setIdPublicador(int idPublicador) {
        this.idPublicador = idPublicador;
    }

    public Map<Integer, Musica> getMusicas() {
        return musicas;
    }

    public void addMusica(int ordem, Musica musica) {
        this.musicas.put(ordem, musica);
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", nome=" + nome + ", dataLancamento=" + dataLancamento + ", precoDisco=" + precoDisco + ", idPublicador=" + idPublicador + '}';
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
