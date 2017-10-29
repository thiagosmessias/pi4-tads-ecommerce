package com.gruposet.ecommerce.models;

import java.sql.Date;

public class Venda {
    
    private int idVenda, idProduto, idCliente, qtdProduto;
    private Date dataCompra;
    private String formaPagamento;
    private Double valorTotal;
    private boolean ativo;

    public Venda(int idProduto, int idCliente, int qtdProduto, Date dataCompra, String formaPagamento) {
        this.idProduto = idProduto;
        this.idCliente = idCliente;
        this.qtdProduto = qtdProduto;
        this.dataCompra = dataCompra;
        this.formaPagamento = formaPagamento;
        this.ativo = true;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
