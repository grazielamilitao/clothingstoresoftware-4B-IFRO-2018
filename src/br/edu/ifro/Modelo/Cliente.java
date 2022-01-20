/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro.Modelo;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Graziela
 */
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int txtCodigo;
    
    private String txtNome;
    private String txtCPF;
    private String txtRG;
    private String dataNasc;
    private String txtDDD;
    private String txtTelefone;
    private String txtCelular;
    private String txtEmail; 
    private String sexo; 
    private String txtRua; 
    private String txtBairro; 
    private String txtNum; 
    private String txtCEP;
    private String cbxCidade; 
    private String cbxEstado; 

    public void setCbxEstado(String cbxEstado) {
        this.cbxEstado = cbxEstado;
    }

    public void setTxtCodigo(int txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public void setTxtNome(String txtNome) {
        this.txtNome = txtNome;
    }

    public void setTxtCPF(String txtCPF) {
        this.txtCPF = txtCPF;
    }

    public void setTxtRG(String txtRG) {
        this.txtRG = txtRG;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setTxtDDD(String txtDDD) {
        this.txtDDD = txtDDD;
    }

    public void setTxtTelefone(String txtTelefone) {
        this.txtTelefone = txtTelefone;
    }

    public void setTxtCelular(String txtCelular) {
        this.txtCelular = txtCelular;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTxtRua(String txtRua) {
        this.txtRua = txtRua;
    }

    public void setTxtBairro(String txtBairro) {
        this.txtBairro = txtBairro;
    }

    public void setTxtNum(String txtNum) {
        this.txtNum = txtNum;
    }

    public void setTxtCEP(String txtCEP) {
        this.txtCEP = txtCEP;
    }

    public void setCbxCidade(String cbxCidade) {
        this.cbxCidade = cbxCidade;
    }
}