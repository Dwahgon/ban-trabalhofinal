/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bantrabalhofinal.persistance;

import bantrabalhofinal.model.Album;
import bantrabalhofinal.model.Musica;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dragonofwar
 */
public class ComposicaoAlbumDAO {

    private static final String SQL_SELECT_JOIN = "select a.id as idalbum,nome,datalancamento,a.idpublicador as aidpublicador,precodisco,m.id as idmusica,titulo,qntvistos,letra,m.idpublicador as midpublicador,ordem from albuns a left join composicoesalbum ca on a.id=ca.idalbum left join musicas m on ca.idmusica=m.id order by a.id,ordem";
    private static final String SQL_INSERT = "insert into composicoesalbum(idalbum,idmusica,ordem) values (?,?,?)";

    private final PreparedStatement selectJoin;
    private final PreparedStatement insert;

    public ComposicaoAlbumDAO(Conexao conexao) throws SQLException {
        selectJoin = conexao.getConnection().prepareStatement(SQL_SELECT_JOIN);
        insert = conexao.getConnection().prepareStatement(SQL_INSERT);
    }

    public void insert(Album album, Musica musica, int ordem) throws SQLException {
        insert.setInt(1, album.getId());
        insert.setInt(2, musica.getId());
        insert.setInt(3, ordem);
        insert.execute();
    }

    public List<Album> selectJoin() throws SQLException {
        List<Album> albuns = new ArrayList<Album>();
        Album albumAtual = null;
        ResultSet rs = selectJoin.executeQuery();
        while (rs.next()) {
            Album album = new Album(rs.getInt("idalbum"));
            album.setNome(rs.getString("nome"));
            album.setDataLancamento(rs.getDate("datalancamento"));
            album.setIdPublicador(rs.getInt("aidpublicador"));
            album.setPrecoDisco(rs.getFloat("precodisco"));

            if (albumAtual == null || !albumAtual.equals(album)) {
                albumAtual = album;
                albuns.add(albumAtual);
            }

            Musica musica = new Musica(rs.getInt("idmusica"));
            if (rs.wasNull()) {
                continue;
            }
            musica.setTitulo(rs.getString("titulo"));
            musica.setQntVistos(rs.getInt("qntvistos"));
            musica.setLetra(rs.getString("letra"));
            musica.setIdPublicador(rs.getInt("midpublicador"));
            albumAtual.addMusica(rs.getInt("ordem"), musica);
        }
        return albuns;
    }
}
