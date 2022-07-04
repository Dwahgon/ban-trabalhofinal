/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package bantrabalhofinal;

import bantrabalhofinal.persistance.Conexao;
import bantrabalhofinal.persistance.MusicaDAO;
import java.sql.SQLException;
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
    
    
    private static MusicaDAO musicaDAO;
    
    
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        try {
            musicaDAO = new MusicaDAO(conexao);
        } catch (SQLException ex) {
            Logger.getLogger(BanTrabalhofinal.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    private static void printMenu(){
        System.out.println(OPCAO_INSERIR_MUSICA+") Inserir nova musica");
        System.out.println(OPCAO_INSERIR_ALBUM+") Inserir novo album");
        System.out.println(OPCAO_LISTAR_MUSICAS+") Listar musicas");
        System.out.println(OPCAO_LISTAR_ALBUNS+") Listar albuns");
        System.out.println(OPCAO_LISTAR_CONTEUDO_ALBUNS+") Listar conteudo albuns");
    }
}
