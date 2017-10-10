/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruposet.ecommerce.models;

/**
 *
 * @author Rafael
 */
public class Produto {

    private int id, estoque;
    private String modelo, marca, descricao, tamanho;
    private double preco;
    private boolean ativo;

    public Produto(int estoque, String modelo, String marca, String descricao, String tamanho, double preco) {
        this.estoque = estoque;
        this.modelo = modelo;
        this.marca = marca;
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.preco = preco;
        this.ativo = true;
    }

    public Produto(int id, int estoque, String modelo, String marca, String descricao, String tamanho, double preco) {
        this.id = id;
        this.estoque = estoque;
        this.modelo = modelo;
        this.marca = marca;
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.preco = preco;
    }

    public Produto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
