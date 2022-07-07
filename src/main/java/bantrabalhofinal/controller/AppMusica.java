/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bantrabalhofinal.controller;

import bantrabalhofinal.model.Album;
import bantrabalhofinal.model.Musica;
import bantrabalhofinal.persistance.AlbumDAO;
import bantrabalhofinal.persistance.ComposicaoAlbumDAO;
import bantrabalhofinal.persistance.Conexao;
import bantrabalhofinal.persistance.MusicaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author udesc
 */
public class AppMusica {

    private final MusicaDAO musicaDAO;
    private final AlbumDAO albumDAO;
    private final ComposicaoAlbumDAO composicaoAlbumDAO;

    public AppMusica(Conexao conexao) throws SQLException {
        musicaDAO = new MusicaDAO(conexao);
        albumDAO = new AlbumDAO(conexao);
        composicaoAlbumDAO = new ComposicaoAlbumDAO(conexao);
    }

    public void cadastrarMusica(Musica musica) throws SQLException {
        this.musicaDAO.insert(musica);
    }

    public List<Musica> consultarMusicas() throws SQLException {
        return this.musicaDAO.selectAll();
    }

    public void cadastrarAlbum(Album album) throws SQLException {
        this.albumDAO.insert(album);
    }

    public List<Album> consultarAlbuns() throws SQLException {
        return this.albumDAO.selectAll();
    }

    public void adicionarMusicaAlbum(Album album, Musica musica, int ordem) throws SQLException {
        this.composicaoAlbumDAO.insert(album, musica, ordem);
    }

    public List<Album> consultarComposicoesAlbuns() throws SQLException {
        return this.composicaoAlbumDAO.selectJoin();
    }
}
