package br.edu.ifro.Modelo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int txtCodigo;
    private String txtValorTotal;
    private String cbxFormaPagamento;
    private String txtValorLiquido;
    private String txtValorBruto;
    private String txtDesconto;
    private String txtJuros;
    private Date dateCompra;
    private String txtNomeProduto;
    private String txtQuantProduto;

    public int getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(int txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public String getTxtValorTotal() {
        return txtValorTotal;
    }

    public void setTxtValorTotal(String txtValorTotal) {
        this.txtValorTotal = txtValorTotal;
    }

    public String getCbxFormaPagamento() {
        return cbxFormaPagamento;
    }

    public void setCbxFormaPagamento(String cbxFormaPagamento) {
        this.cbxFormaPagamento = cbxFormaPagamento;
    }

    public String getTxtValorLiquido() {
        return txtValorLiquido;
    }

    public void setTxtValorLiquido(String txtValorLiquido) {
        this.txtValorLiquido = txtValorLiquido;
    }

    public String getTxtValorBruto() {
        return txtValorBruto;
    }

    public void setTxtValorBruto(String txtValorBruto) {
        this.txtValorBruto = txtValorBruto;
    }

    public String getTxtDesconto() {
        return txtDesconto;
    }

    public void setTxtDesconto(String txtDesconto) {
        this.txtDesconto = txtDesconto;
    }

    public String getTxtJuros() {
        return txtJuros;
    }

    public void setTxtJuros(String txtJuros) {
        this.txtJuros = txtJuros;
    }

    public Date getDateCompra() {
        return dateCompra;
    }

    public void setDateCompra(Date dateCompra) {
        this.dateCompra = dateCompra;
    }

    public String getTxtNomeProduto() {
        return txtNomeProduto;
    }

    public void setTxtNomeProduto(String txtNomeProduto) {
        this.txtNomeProduto = txtNomeProduto;
    }

    public String getTxtQuantProduto() {
        return txtQuantProduto;
    }

    public void setTxtQuantProduto(String txtQuantProduto) {
        this.txtQuantProduto = txtQuantProduto;
    }
    
}
