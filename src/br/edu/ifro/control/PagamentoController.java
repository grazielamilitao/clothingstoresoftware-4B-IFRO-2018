package br.edu.ifro.control;

import br.edu.ifro.Modelo.Fornecedor;
import br.edu.ifro.Modelo.Pagamento;
import br.edu.ifro.Modelo.Produto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PagamentoController implements Initializable {
    
    @FXML
    private TextField txtCompra;
    @FXML
    private TextField txtQuantProduto;
    @FXML
    private TextField txtValor;
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
    private JFXTextField txtFornecedor;
    @FXML
    private JFXComboBox<?> cbxTipoPagamento;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private StackPane cena;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    private void cancelar() {
        Stage stage = (Stage ) btnCancelar.getScene().getWindow();
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
        if(txtCompra.getText().equals("")){
           mensagem += "\n - Campo código compra deve ser preenchido";
        }
        if (txtQuantProduto.getText().equals("")) {               
           mensagem += "\n - Campo quantidade de produtos deve ser preenchido";
        }
        if(dateLancamento.getValue() == null){
            mensagem += "\n - Campo data de lançamento deve ser preenchido";
        }
        if(dateVencimento.getValue() == null){
            mensagem += "\n - Campo data de vencimento deve ser preenchido";
        }
         if(txtFornecedor.getText().equals("")){
            mensagem += "\n - Campo fornecedor deve ser preenchido";
        }
         if(txtValor.getText().equals("")){
            mensagem += "\n - Campo valor compra deve ser preenchido";
        }
         if(cbxTipoPagamento.getPromptText().equals("")){
            mensagem += "\n - Campo forma de pagamento deve ser preenchido";
        }
         if(datePagamento.getValue() == null){
           mensagem += "\n - Campo data de pagamento deve ser preenchido";
        }
         if(txtValorCopia.getText().equals("")){
           mensagem += "\n - Campo valor da compra deve ser preenchido";
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
          if (!mensagem.equals("")) {
            abrirTelaErro(mensagem);
        }
        else{
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetosistemaDS");
                EntityManager em = emf.createEntityManager();
                Pagamento p =new Pagamento();
                p.setTxtCompra(txtCompra.getText());
                p.setTxtQuantProduto(txtQuantProduto.getText());
                p.setTxtFornecedor(txtFornecedor.getText());
                
                LocalDate ldLancamento = dateLancamento.getValue();
                Instant iLancamento = Instant.from(ldLancamento.atStartOfDay(ZoneId.systemDefault()));
                java.util.Date dLancamento = java.util.Date.from(iLancamento);            
                p.setLancamento( (Date) dLancamento);

                LocalDate ldVencimento = dateVencimento.getValue();
                Instant iVencimento = Instant.from(ldVencimento.atStartOfDay(ZoneId.systemDefault()));
                java.util.Date dVencimento = java.util.Date.from(iVencimento);            
                p.setVencimento( (Date) dVencimento);

                LocalDate ldPagamento = datePagamento.getValue();
                Instant iPagamento = Instant.from(ldPagamento.atStartOfDay(ZoneId.systemDefault()));
                java.util.Date dPagamento = java.util.Date.from(iPagamento);            
                p.setDataPagamento( (Date) dPagamento);
                p.setTxtValorTotal(txtValorTotal.getText());
                p.setTxtValorCopia(txtValorCopia.getText());
                p.setTxtDesconto(txtDesconto.getText());
                p.setTxtJuros(txtJuros.getText());
                p.setTxtValorAtualizado(txtValorAtualizado.getText());
                p.setCbxTipoPagamento((String) cbxTipoPagamento.getSelectionModel().getSelectedItem()); 

                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
            
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println(e.getStackTrace().length);
                System.out.println(e.getMessage());
            }
            cancelar();
      }
    }
    
}