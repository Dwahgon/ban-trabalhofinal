/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bantrabalhofinal.model;

/**
 *
 * @author udesc
 */
public class Publicador extends Conta{

    public Publicador(int id) {
        super(id);
    }
    
    @Override
    public String toString() {
        return "Publicador{" + "id=" + id + ", dataNascimento=" + dataNascimento + ", nome=" + nome + ", email=" + email + '}';
    }
}
