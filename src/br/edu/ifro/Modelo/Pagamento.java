
package br.edu.ifro.Modelo;
import java.sql.Date;
import javafx.scene.control.ComboBox;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    
    private String txtCompra;
    private String txtQuantProduto;
    private Date lancamento;
    private Date vencimento;
    private String txtFornecedor;
    private String txtValor;
    private String cbxTipoPagamento;
    private Date dataPagamento;   
    private String txtValorCopia; 
    private String txtValorTotal;
    private String txtDesconto;
    private String txtJuros;
    private String txtValorAtualizado;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTxtCompra() {
        return txtCompra;
    }

    public void setTxtCompra(String txtCompra) {
        this.txtCompra = txtCompra;
    }
    
    public String getTxtQuantProduto() {
        return txtQuantProduto;
    }

    public void setTxtQuantProduto(String txtQuantProduto) {
        this.txtQuantProduto = txtQuantProduto;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getCbxTipoPagamento() {
        return cbxTipoPagamento;
    }

    public void setCbxTipoPagamento(String cbxTipoPagamento) {
        this.cbxTipoPagamento = cbxTipoPagamento;
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
    
    public String getTxtValorTotal() {
        return txtValorTotal;
    }

    public void setTxtValorTotal(String txtValorTotal) {
        this.txtValorTotal = txtValorTotal;
    }
    
    public String getTxtFornecedor() {
        return txtFornecedor;
    }

    public void setTxtFornecedor(String txtFornecedor) {
        this.txtFornecedor = txtFornecedor;
    }
    
    public String getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(String txtValor) {
        this.txtValor = txtValor;
    }

    public String getTxtValorCopia() {
        return txtValorCopia;
    }

    public void setTxtValorCopia(String txtValorCopia) {
        this.txtValorCopia = txtValorCopia;
    }
}