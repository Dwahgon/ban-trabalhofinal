/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package bantrabalhofinal;

import bantrabalhofinal.controller.AppMusica;
import bantrabalhofinal.model.Album;
import bantrabalhofinal.model.Musica;
import bantrabalhofinal.persistance.Conexao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author udesc
 */
public class BanTrabalhofinal {

    private static final int OPCAO_INSERIR_MUSICA = 1;
    private static final int OPCAO_INSERIR_ALBUM = 2;
    private static final int OPCAO_LISTAR_MUSICAS = 3;
    private static final int OPCAO_LISTAR_ALBUNS = 4;
    private static final int OPCAO_ADD_MUSICA_ALBUM = 5;
    private static final int OPCAO_LISTAR_CONTEUDO_ALBUNS = 6;

    private static AppMusica appMusica;
    private static Scanner scanner;

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        try {
            appMusica = new AppMusica(conexao);
        } catch (SQLException ex) {
            Logger.getLogger(BanTrabalhofinal.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }

        scanner = new Scanner(System.in);

        menu();

        scanner.close();
        conexao.closeConnection();
    }

    private static void menu() {
        loop:
        do {
            printMenu();
            try {
                switch (Integer.valueOf(scanner.nextLine())) {
                    case OPCAO_INSERIR_ALBUM:
                        cadastrarAlbum();
                        break;
                    case OPCAO_INSERIR_MUSICA:
                        cadastrarMusica();
                        break;
                    case OPCAO_LISTAR_ALBUNS:
                        consultarAlbuns();
                        break;
                    case OPCAO_LISTAR_MUSICAS:
                        consultarMusicas();
                        break;
                    case OPCAO_ADD_MUSICA_ALBUM:
                        addMusicaAlbum();
                        break;
                    case OPCAO_LISTAR_CONTEUDO_ALBUNS:
                        consultarMusicasDeAlbum();
                        break;

                }
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida");
            } catch (SQLException ex) {
                System.out.println("Erro de SQL. Mensagem de erro: " + ex.getMessage());
            } catch (UnsupportedOperationException ex) {
                System.out.println("Operação não implementada");
            } catch (ParseException ex) {
                System.out.println("Data inválida");
            }
        } while (true);
    }

    private static void printMenu() {
        System.out.println(OPCAO_INSERIR_MUSICA + ") Inserir nova musica");
        System.out.println(OPCAO_INSERIR_ALBUM + ") Inserir novo album");
        System.out.println(OPCAO_LISTAR_MUSICAS + ") Listar musicas");
        System.out.println(OPCAO_LISTAR_ALBUNS + ") Listar albuns");
        System.out.println(OPCAO_ADD_MUSICA_ALBUM + ") Adicionar música à album");
        System.out.println(OPCAO_LISTAR_CONTEUDO_ALBUNS + ") Listar conteúdo albuns");
    }

    private static void cadastrarMusica() throws SQLException {
        Musica musica = promptMusica();
        appMusica.cadastrarMusica(musica);
    }

    private static Musica promptMusica() {
        Musica musica = new Musica(0);
        System.out.println("Criando música:");
        System.out.print("Título: ");
        musica.setTitulo(scanner.nextLine());
        System.out.print("Letra: ");
        musica.setLetra(scanner.nextLine());
        musica.setQntVistos(0);
        musica.setIdPublicador(1);

        return musica;
    }

    private static void consultarMusicas() throws SQLException {
        List<Musica> musicas = appMusica.consultarMusicas();
        musicas.forEach((musica) -> {
            System.out.println(musica);
        });
    }

    private static void cadastrarAlbum() throws ParseException, SQLException {
        Album album = promptAlbum();
        appMusica.cadastrarAlbum(album);
    }

    private static Album promptAlbum() throws ParseException {
        Album album = new Album(0);
        System.out.println("Criando album: ");
        System.out.print("Nome: ");
        album.setNome(scanner.nextLine());
        System.out.print("Preço do disco: ");
        album.setPrecoDisco(Float.valueOf(scanner.nextLine()));
        System.out.print("Data de lançamento (dd/MM/yyyy): ");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        album.setDataLancamento(formatter.parse(scanner.nextLine()));
        album.setIdPublicador(1);
        return album;
    }

    private static Album promptSelecionarAlbum() throws SQLException {
        List<Album> albuns = appMusica.consultarAlbuns();
        int i = 1;
        for (Album album : albuns) {
            System.out.println("(" + i + ") " + album);
            i++;
        }

        int numAlbumSelecionado;
        do {
            System.out.print("Digite o número da álbum: ");
            numAlbumSelecionado = Integer.valueOf(scanner.nextLine());
            if (numAlbumSelecionado > 0 && numAlbumSelecionado <= albuns.size()) {
                return albuns.get(numAlbumSelecionado - 1);
            }
            System.out.println("O número deve estar entre 1 e " + albuns.size());
        } while (true);
    }

    private static Musica promptSelecionarMusica() throws SQLException {
        List<Musica> musicas = appMusica.consultarMusicas();

        if (musicas.size() == 0) {
            return null;
        }

        int i = 1;
        for (Musica musica : musicas) {
            System.out.println("(" + i + ") " + musica);
            i++;
        }

        int numMusicaSelecionado;
        do {
            System.out.print("Digite o número da música: ");
            numMusicaSelecionado = Integer.valueOf(scanner.nextLine());
            if (numMusicaSelecionado > 0 && numMusicaSelecionado <= musicas.size()) {
                return musicas.get(numMusicaSelecionado - 1);
            }
            System.out.println("O número deve estar entre 1 e " + musicas.size());
        } while (true);
    }

    private static void consultarAlbuns() throws SQLException {
        List<Album> albuns = appMusica.consultarAlbuns();
        albuns.forEach((album) -> {
            System.out.println(album);
        });
    }

    private static void consultarMusicasDeAlbum() throws SQLException {
        List<Album> albuns = appMusica.consultarComposicoesAlbuns();
        for (Album album : albuns) {
            System.out.println(album);
            for (Map.Entry<Integer, Musica> entrada : album.getMusicas().entrySet()) {
                System.out.println("\t" + entrada.getKey() + ": " + entrada.getValue());
            }
        }
    }

    private static void addMusicaAlbum() throws SQLException {
        Album album = promptSelecionarAlbum();
        if (album == null) {
            System.out.println("Não há albuns cadastrados");
            return;
        }
        Musica musica = promptSelecionarMusica();
        if (musica == null) {
            System.out.println("Não há músicas cadastrados");
            return;
        }
        System.out.print("Ordem: ");
        int ordem = Integer.valueOf(scanner.nextLine());
        appMusica.adicionarMusicaAlbum(album, musica, ordem);
    }
}
