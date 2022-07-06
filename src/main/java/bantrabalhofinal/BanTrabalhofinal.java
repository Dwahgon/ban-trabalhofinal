/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package bantrabalhofinal;

import bantrabalhofinal.controller.AppMusica;
import bantrabalhofinal.model.Album;
import bantrabalhofinal.model.Musica;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    private static final int OPCAO_LISTAR_CONTEUDO_ALBUNS = 5;
    
    private static AppMusica appMusica;
    private static Scanner scanner;
    
    public static void main(String[] args){
        try {
            appMusica = new AppMusica();
        } catch (SQLException ex) {
            Logger.getLogger(BanTrabalhofinal.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
        scanner = new Scanner(System.in);
        
        menu();
        
        scanner.close();
    }
    
    private static void menu(){
        loop:
        do {
            printMenu();
            try{
                switch(Integer.valueOf(scanner.nextLine())){
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
                    case OPCAO_LISTAR_CONTEUDO_ALBUNS:
                        consultarMusicasDeAlbum();
                        break;
                        
                }
            }catch (NumberFormatException ex){
                System.out.println("Entrada inválida");
            } catch (SQLException ex) {
                System.out.println("Erro de SQL. Mensagem de erro: "+ex.getMessage());
            } catch (UnsupportedOperationException ex) {
                System.out.println("Operação não implementada");
            } catch (ParseException ex) {
                System.out.println("Data inválida");
            }
        }while(true);
    }
    
    private static void printMenu(){
        System.out.println(OPCAO_INSERIR_MUSICA+") Inserir nova musica");
        System.out.println(OPCAO_INSERIR_ALBUM+") Inserir novo album");
        System.out.println(OPCAO_LISTAR_MUSICAS+") Listar musicas");
        System.out.println(OPCAO_LISTAR_ALBUNS+") Listar albuns");
        System.out.println(OPCAO_LISTAR_CONTEUDO_ALBUNS+") Listar conteúdo albuns");
    }
    
    private static void cadastrarMusica() throws SQLException{
        Musica musica = promptMusica();
        appMusica.cadastrarMusica(musica);
    }
    
    private static Musica promptMusica(){
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
        for (Musica musica : musicas)
            System.out.println(musica);
    }

    private static void cadastrarAlbum() throws ParseException, SQLException {
        Album album = promptAlbum();
        appMusica.cadastrarAlbum(album);
    }
    
    private static Album promptAlbum() throws ParseException{
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

    private static void consultarAlbuns() throws SQLException {
        List <Album> albuns = appMusica.consultarAlbuns();
        for (Album album : albuns)
            System.out.println(album);
    }

    private static void consultarMusicasDeAlbum() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
