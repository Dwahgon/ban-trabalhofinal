/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bantrabalhofinal.persistance;

import bantrabalhofinal.model.Musica;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author udesc
 */
public class MusicaDAO {

    private static final String SQL_SELECT_ALL = "select * from musicas";
    private static final String SQL_SELECT = "select * from musicas where id=?";
    private static final String SQL_INSERT = "insert into musicas(titulo,qntVistos,letra,idpublicador) values(?,?,?,?)";
    private static final String SQL_UPDATE = "update table musicas set titulo=?, qntVistos=?, letra=?, idpublicador=? where id=?";

    private final PreparedStatement selectAll;
    private final PreparedStatement select;
    private final PreparedStatement insert;
    private final PreparedStatement update;
    
    public MusicaDAO(Conexao conexao) throws SQLException {
        this.selectAll = conexao.getConnection().prepareStatement(SQL_SELECT_ALL);
        this.select = conexao.getConnection().prepareStatement(SQL_SELECT);
        this.insert = conexao.getConnection().prepareStatement(SQL_INSERT);
        this.update = conexao.getConnection().prepareStatement(SQL_UPDATE);
    }
    
    public List<Musica> selectAll() throws SQLException{
        List<Musica> musicas = new ArrayList<>();
        ResultSet resultSet = this.selectAll.executeQuery();
        while (resultSet.next())
            musicas.add(new Musica(resultSet));
        return musicas;
    }
    
    public Musica select(int id) throws SQLException{
        this.select.setInt(0, id);
        ResultSet resultSet = this.select.executeQuery();
        if (resultSet.next())
            return new Musica(resultSet);
        return null;
    }
    
    public void insert(Musica musica) throws SQLException {
        this.insert.setString(1, musica.getTitulo());
        this.insert.setInt(2,0);
        this.insert.setString(3, musica.getLetra());
        this.insert.setInt(4, musica.getIdPublicador());
        this.insert.execute();
    }
    
    public void update(Musica musica) throws SQLException {
        this.update.setString(1, musica.getTitulo());
        this.update.setInt(2,0);
        this.update.setString(3, musica.getLetra());
        this.update.setInt(4, musica.getIdPublicador());
        this.update.setInt(5, musica.getId());
        this.update.execute();
    }
}
