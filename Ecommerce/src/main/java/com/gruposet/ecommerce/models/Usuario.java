package com.gruposet.ecommerce.models;

public class Usuario {

    private int id;
    private String nome, apelido, cpf, data_nasc, 
            telefone, email, senha, perfil;
    private boolean ativo;

    public Usuario(int id) {
        this.id = id;
    }

    public Usuario(String nome, String apelido, String cpf, 
            String data_nasc, String telefone, String email, 
            String senha, boolean ativo) {
        this.nome = nome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.data_nasc = data_nasc;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.perfil = "cliente";
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
