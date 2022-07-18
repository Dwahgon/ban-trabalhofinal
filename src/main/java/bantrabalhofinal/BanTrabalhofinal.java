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
    private static final int OPCAO_LISTAR_MAIS_CARO = 7;
    private static final int OPCAO_SAIR = 8;

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
                    case OPCAO_LISTAR_MAIS_CARO:
                        consultarMusicaMaisCara();
                        break;
                    case OPCAO_SAIR:
                        break loop;
                    default:
                        System.out.println("Entrada inválida");
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
        System.out.println(OPCAO_LISTAR_MAIS_CARO + ") Listar albuns mais caros");
        System.out.println(OPCAO_SAIR + ") Sair");
    }

    private static void cadastrarMusica() throws SQLException {
        System.out.println("Cadastrando música:");
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
        System.out.println("Músicas:");
        List<Musica> musicas = appMusica.consultarMusicas();
        musicas.forEach((musica) -> {
            System.out.println(musica);
        });
    }

    private static void cadastrarAlbum() throws ParseException, SQLException {
        System.out.println("Cadastrando album:");
        Album album = promptAlbum();
        appMusica.cadastrarAlbum(album);
    }

    private static void consultarAlbuns() throws SQLException {
        System.out.println("Albuns:");
        List<Album> albuns = appMusica.consultarAlbuns();
        albuns.forEach((album) -> {
            System.out.println(album);
        });
    }

    private static void consultarMusicasDeAlbum() throws SQLException {
        System.out.println("Albuns e suas músicas:");
        List<Album> albuns = appMusica.consultarComposicoesAlbuns();
        for (Album album : albuns) {
            System.out.println(album);
            for (Map.Entry<Integer, Musica> entrada : album.getMusicas().entrySet()) {
                System.out.println("\t" + entrada.getKey() + ": " + entrada.getValue());
            }
        }
    }

    private static void addMusicaAlbum() throws SQLException {
        System.out.println("Adicionando músicas à albuns");
        Album album = promptSelecionarObjeto(appMusica.consultarAlbuns());
        if (album == null) {
            System.out.println("Não há albuns cadastrados");
            return;

        }
        Musica musica = promptSelecionarObjeto(appMusica.consultarMusicas());
        if (musica == null) {
            System.out.println("Não há músicas cadastrados");
            return;
        }
        System.out.print("Ordem: ");
        int ordem = Integer.valueOf(scanner.nextLine());
        appMusica.adicionarMusicaAlbum(album, musica, ordem);
    }

    private static void consultarMusicaMaisCara() throws SQLException {
        List<Album> albuns = appMusica.consultarAlbumMaisCaro();
        if (albuns.isEmpty()){
            System.out.println("Nenhum album encontrado");
            return;
        }
        
        System.out.println("Albuns mais caros:");
        for (Album album : albuns){
            System.out.println(album);
        }
        
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

    private static <T> T promptSelecionarObjeto(List<T> options) {
        if (options.isEmpty()) {
            return null;
        }

        int i = 1;
        for (T option : options) {
            System.out.println("(" + i + ") " + option);
            i++;
        }

        int optionNum;
        do {
            System.out.println("Digite a opção que deseja selecionar: ");
            optionNum = Integer.valueOf(scanner.nextLine());
            if (optionNum > 0 && optionNum <= options.size()) {
                return options.get(optionNum - 1);
            }
            System.out.println("O número deve estar entre 1 e " + options.size());
        } while (true);
    }
}
