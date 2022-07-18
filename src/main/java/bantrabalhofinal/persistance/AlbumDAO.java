/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bantrabalhofinal.persistance;

import bantrabalhofinal.model.Album;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author udesc
 */
public class AlbumDAO {

    private static final String SQL_SELECT_ALL = "select * from albuns";
    private static final String SQL_SELECT_MOST_EXPENSIVE = "select * from albuns where precodisco=any(select max(precodisco) from albuns) order by id";
    private static final String SQL_INSERT = "insert into albuns(nome,datalancamento,idpublicador,precodisco) values (?,?,?,?)";

    private final PreparedStatement selectAll;
    private final PreparedStatement selectMostExpensive;
    private final PreparedStatement insert;

    public AlbumDAO(Conexao conexao) throws SQLException {
        selectAll = conexao.getConnection().prepareStatement(SQL_SELECT_ALL);
        insert = conexao.getConnection().prepareStatement(SQL_INSERT);
        selectMostExpensive = conexao.getConnection().prepareStatement(SQL_SELECT_MOST_EXPENSIVE);
    }

    public List<Album> selectAll() throws SQLException {
        List<Album> albuns = new ArrayList<>();
        ResultSet resultSet = selectAll.executeQuery();
        while (resultSet.next()) {
            albuns.add(new Album(resultSet));
        }
        return albuns;
    }

    public void insert(Album album) throws SQLException {
        insert.setString(1, album.getNome());
        insert.setDate(2, new Date(album.getDataLancamento().getTime()));
        insert.setInt(3, album.getIdPublicador());
        insert.setFloat(4, album.getPrecoDisco());
        insert.execute();
    }

    public List<Album> selectMostExpensive() throws SQLException {
        List<Album> albuns = new ArrayList<>();
        ResultSet resultSet = selectMostExpensive.executeQuery();
        while (resultSet.next()) {
            albuns.add(new Album(resultSet));
        }
        return albuns;
    }

}
