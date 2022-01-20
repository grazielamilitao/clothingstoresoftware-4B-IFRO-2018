/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro.control;

import br.edu.ifro.Modelo.Cliente;
import br.edu.ifro.Modelo.Recebimento;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RecebimentoController implements Initializable {

    @FXML
    private JFXTextField txtCodVenda;
    @FXML
    private JFXTextField txtQuantProd;
    @FXML
    private JFXTextField txtValor;
    @FXML
    private DatePicker dateLancamento;
    @FXML
    private DatePicker dateVencimento;
    @FXML
    private DatePicker datePagamento;
    @FXML
    private JFXTextField txtValorCopia;
    @FXML
    private JFXTextField txtValorTotal;
    @FXML
    private JFXTextField txtDesconto;
    @FXML
    private JFXTextField txtJuros;
    @FXML
    private JFXTextField txtValorAtualizado;
    @FXML
    private JFXButton btnPagamento;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXComboBox<?> cbxTipoPagamento;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private StackPane cena;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList items = FXCollections.observableArrayList();
        items.add("A vista");
        items.add("Parcelado em 1x no cartão");
        items.add("Parcelado em 2x no cartão");
        items.add("Parcelado em 3x no cartão");
        items.add("Parcelado em 4x no cartão");
        items.add("Parcelado em 5x no cartão");
        items.add("Parcelado em 6x no cartão");
        cbxTipoPagamento.getItems().addAll(items);
        
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
     private void abrirTelaErro(String mensagem) throws IOException {
            JFXDialogLayout content =new JFXDialogLayout();
            content.setHeading(new Text("Erro"));
            content.setBody(new Text(mensagem));
            JFXDialog dialog= new JFXDialog(cena,content, JFXDialog.DialogTransition.CENTER);
            JFXButton btn= new JFXButton("Fechar");
            btn.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                }
            });
            content.setActions(btn);
            dialog.show();
    }

    @FXML
    private void pagamento(ActionEvent event) throws IOException {
        String mensagem = "";       
        if(txtCodVenda.getText().equals("")){
           mensagem += "\n - Campo código da venda deve ser preenchido";
        }
         if(txtQuantProd.getText().equals("")){
            mensagem += "\n - Campo quantidade de produtos deve ser preenchido";
        }
         if(dateLancamento.getValue() == null){
           mensagem += "\n - Campo lançamento deve ser preenchido";
        }
         if(dateVencimento.getValue() == null){
            mensagem += "\n - Campo vencimento deve ser preenchido";
        }
         if(txtCliente.getPromptText().equals("")){
          mensagem += "\n - Campo cliente deve ser preenchido";
        }
         if (txtValor.getText().equals("")) {               
           mensagem += "\n - Campo valor compra deve ser preenchido";
        }
         if(cbxTipoPagamento.getPromptText().equals("")){
          mensagem += "\n - Campo forma de pagamento deve ser preenchido";
        }
         if(datePagamento.getValue() == null){
           mensagem += "\n - Campo data recebimento deve ser preenchido";
        }
         if(txtValorCopia.getText().equals("")){
           mensagem += "\n - Campo valor compra deve ser preenchido";
        }
         if(txtValorTotal.getText().equals("")){
           mensagem += "\n - Campo valor total deve ser preenchido";
        }
         if(txtDesconto.getText().equals("")){
           mensagem += "\n - Campo desconto deve ser preenchido";
        }
         if(txtJuros.getText().equals("")){
         mensagem += "\n - Campo juros deve ser preenchido";
        }
         if(txtValorAtualizado.getText().equals("")){
         mensagem += "\n - Campo valor atualizado deve ser preenchido";
        }
        else{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetosistemaDS");
            EntityManager em = emf.createEntityManager();

            Recebimento r = new Recebimento();
            r.setTxtCodVenda(txtCodVenda.getText());
            r.setTxtQuantProd(txtQuantProd.getText());
            r.setTxtValor(txtValor.getText());
            
            LocalDate ldLancamento = dateLancamento.getValue();
            Instant iLancamento = Instant.from(ldLancamento.atStartOfDay(ZoneId.systemDefault()));
            Date dLancamento = Date.from(iLancamento);            
            r.setDateLancamento(dLancamento);
            
            LocalDate ldVencimento = dateVencimento.getValue();
            Instant iVencimento = Instant.from(ldVencimento.atStartOfDay(ZoneId.systemDefault()));
            Date dVencimento = Date.from(iVencimento);            
            r.setDateVencimento(dVencimento);
            
            LocalDate ldPagamento = datePagamento.getValue();
            Instant iPagamento = Instant.from(ldPagamento.atStartOfDay(ZoneId.systemDefault()));
            Date dPagamento = Date.from(iPagamento);            
            r.setDateLancamento(dPagamento);
          
            r.setTxtValorCopia(txtValorCopia.getText());
            r.setTxtValorTotal(txtValorTotal.getText());
            r.setTxtDesconto(txtDesconto.getText());
            r.setTxtJuros(txtJuros.getText());
            r.setTxtValorAtualizado(txtValorAtualizado.getText());
            r.setCbxTipoPagamento((String) cbxTipoPagamento.getSelectionModel().getSelectedItem()); 
            r.setTxtJuros(txtCliente.getText());
            
            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();
             
    }
    }
}
