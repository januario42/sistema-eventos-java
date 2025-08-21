package model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String email;
    private String telefone;
    private int idade;
    
    public User(String nome, String email, String telefone, int idade) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
    }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    
    @Override
    public String toString() {
        return String.format("Nome: %s | Email: %s | Telefone: %s | Idade: %d", 
                           nome, email, telefone, idade);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User usuario = (User) obj;
        return email.equals(usuario.email);
    }
    
    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
