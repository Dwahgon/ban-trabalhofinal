/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bantrabalhofinal.model;

import java.util.Date;

/**
 *
 * @author udesc
 */
public class Conta {
    protected final int id;
    protected Date dataNascimento;
    protected String nome;
    protected String email;

    public Conta(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Conta{" + "id=" + id + ", dataNascimento=" + dataNascimento + ", nome=" + nome + ", email=" + email + '}';
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
        final Conta other = (Conta) obj;
        return this.id == other.id;
    }
    
    
}
