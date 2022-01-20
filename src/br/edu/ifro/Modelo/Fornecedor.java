package br.edu.ifro.Modelo;
import javafx.scene.control.ComboBox;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    
    private String txtNomeFantasia;
    private String txtTelefone;
    private String txtTipoDeProduto;
    private String txtRazaoSocial;
    private String txtEmail;
    private String txtCNPJ;
    private String cbxEstado;
    private String cbxCidade;
    private String txtBairro;
    private String txtRua; 
    private String txtNumero; 
    private String txtCEP;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTxtNomeFantasia() {
        return txtNomeFantasia;
    }

    public void setTxtNomeFantasia(String txtNomeFantasia) {
        this.txtNomeFantasia = txtNomeFantasia;
    }

    public String getTxtTelefone() {
        return txtTelefone;
    }

    public void setTxtTelefone(String txtyTelefone) {
        this.txtTelefone = txtyTelefone;
    }

    public String getTxtTipoDeProduto() {
        return txtTipoDeProduto;
    }

    public void setTxtTipoDeProduto(String txtTipoDeProduto) {
        this.txtTipoDeProduto = txtTipoDeProduto;
    }

    public String getTxtRazaoSocial() {
        return txtRazaoSocial;
    }

    public void setTxtRazaoSocial(String txtRazaoSocial) {
        this.txtRazaoSocial = txtRazaoSocial;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtCNPJ() {
        return txtCNPJ;
    }

    public void setTxtCNPJ(String txtCNPJ) {
        this.txtCNPJ = txtCNPJ;
    }

    public String getCbxEstado() {
        return cbxEstado;
    }

    public void setCbxEstado(String cbxEstado) {
        this.cbxEstado = cbxEstado;
    }

    public String getCbxCidade() {
        return cbxCidade;
    }

    public void setCbxCidade(String cbxCidade) {
        this.cbxCidade = cbxCidade;
    }

    public String getTxtBairro() {
        return txtBairro;
    }

    public void setTxtBairro(String txtBairro) {
        this.txtBairro = txtBairro;
    }

    public String getTxtRua() {
        return txtRua;
    }

    public void setTxtRua(String txtRua) {
        this.txtRua = txtRua;
    }

    public String getTxtNumero() {
        return txtNumero;
    }

    public void setTxtNumero(String txtNumero) {
        this.txtNumero = txtNumero;
    }

    public String getTxtCEP() {
        return txtCEP;
    }

    public void setTxtCEP(String txtCEP) {
        this.txtCEP = txtCEP;
    }

   
    

   
    }
