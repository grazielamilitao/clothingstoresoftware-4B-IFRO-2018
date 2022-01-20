package br.edu.ifro.Modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int txtCodigo;
    private String txtNome;
    private String txtDescricao;

    public int getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(int txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public String getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(String txtNome) {
        this.txtNome = txtNome;
    }

    public String getTxtDescricao() {
        return txtDescricao;
    }

    public void setTxtDescricao(String txtDescricao) {
        this.txtDescricao = txtDescricao;
    }
    
    
}