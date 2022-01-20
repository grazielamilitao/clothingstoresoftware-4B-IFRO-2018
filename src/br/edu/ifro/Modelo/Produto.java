package br.edu.ifro.Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int txtCodigo;
    private String txtTamanho, txtDescricao, txtNome, txtMarca, cbxFornecedor;
    private int txtEstoque;
    private double txtCusto,txtVarejo,txtAtacado;
    
    public String toString() {
        return txtCodigo + " - " + txtDescricao;
    }

    public void setTxtCodigo(int txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public void setTxtTamanho(String txtTamanho) {
        this.txtTamanho = txtTamanho;
    }

    public void setTxtDescricao(String txtDescricao) {
        this.txtDescricao = txtDescricao;
    }

    public void setTxtNome(String txtNome) {
        this.txtNome = txtNome;
    }

    public void setTxtMarca(String txtMarca) {
        this.txtMarca = txtMarca;
    }
    
    public void setCbxFornecedor(String cbxFornecedor) {
        this.cbxFornecedor = cbxFornecedor;
    }

    public void setTxtEstoque(int txtEstoque) {
        this.txtEstoque = txtEstoque;
    }

    public void setTxtCusto(double txtCusto) {
        this.txtCusto = txtCusto;
    }

    public void setTxtVarejo(double txtVarejo) {
        this.txtVarejo = txtVarejo;
    }

    public void setTxtAtacado(double txtAtacado) {
        this.txtAtacado = txtAtacado;
    }

    public int getTxtCodigo() {
        return txtCodigo;
    }

    public String getTxtTamanho() {
        return txtTamanho;
    }

    public String getTxtDescricao() {
        return txtDescricao;
    }

    public String getTxtNome() {
        return txtNome;
    }

    public String getTxtMarca() {
        return txtMarca;
    }

    public String getCbxFornecedor() {
        return cbxFornecedor;
    }

    public int getTxtEstoque() {
        return txtEstoque;
    }

    public double getTxtCusto() {
        return txtCusto;
    }

    public double getTxtVarejo() {
        return txtVarejo;
    }

    public double getTxtAtacado() {
        return txtAtacado;
    }
 
    
}
