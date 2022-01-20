package br.edu.ifro.Modelo;
import com.jfoenix.controls.JFXComboBox;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// @Entity
public class Recebimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @FXML
    private Integer codigo;
    @FXML
    private String txtCodVenda;
    @FXML
    private String txtQuantProd;
    @FXML
    private String txtValor;
    @FXML
    private Date dateLancamento;
    @FXML
    private Date dateVencimento;
    @FXML
    private Date datePagamento;
    @FXML
    private String txtValorCopia;
    @FXML
    private String txtValorTotal;
    @FXML
    private String txtDesconto;
    @FXML
    private String txtJuros;
    @FXML
    private String txtValorAtualizado;
    @FXML
    private String cbxTipoPagamento;
    @FXML
    private String txtCliente;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTxtCodVenda() {
        return txtCodVenda;
    }

    public void setTxtCodVenda(String txtCodVenda) {
        this.txtCodVenda = txtCodVenda;
    }

    public String getTxtQuantProd() {
        return txtQuantProd;
    }

    public void setTxtQuantProd(String txtQuantProd) {
        this.txtQuantProd = txtQuantProd;
    }

    public String getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(String txtValor) {
        this.txtValor = txtValor;
    }

    public Date getDateLancamento() {
        return dateLancamento;
    }

    public void setDateLancamento(Date dateLancamento) {
        this.dateLancamento = dateLancamento;
    }

    public Date getDateVencimento() {
        return dateVencimento;
    }

    public void setDateVencimento(Date dateVencimento) {
        this.dateVencimento = dateVencimento;
    }

    public Date getDatePagamento() {
        return datePagamento;
    }

    public void setDatePagamento(Date datePagamento) {
        this.datePagamento = datePagamento;
    }

    public String getTxtValorCopia() {
        return txtValorCopia;
    }

    public void setTxtValorCopia(String txtValorCopia) {
        this.txtValorCopia = txtValorCopia;
    }

    public String getTxtValorTotal() {
        return txtValorTotal;
    }

    public void setTxtValorTotal(String txtValorTotal) {
        this.txtValorTotal = txtValorTotal;
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

    public String getTxtValorAtualizado() {
        return txtValorAtualizado;
    }

    public void setTxtValorAtualizado(String txtValorAtualizado) {
        this.txtValorAtualizado = txtValorAtualizado;
    }

    public String getCbxTipoPagamento() {
        return cbxTipoPagamento;
    }

    public void setCbxTipoPagamento(String cbxTipoPagamento) {
        this.cbxTipoPagamento = cbxTipoPagamento;
    }

    public String getTxtClient() {
        return txtCliente;
    }

    public void setTxtCliente(String txtCliente) {
        this.txtCliente = txtCliente;
    }
   
    
}  