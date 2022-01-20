package br.edu.ifro.control;

import br.edu.ifro.Modelo.Fornecedor;
import br.edu.ifro.Modelo.Produto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FornecedorController implements Initializable {
    
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNomeFantasia;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtTipoDeProduto;
    @FXML
    private TextField txtRazaoSocial;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCNPJ;
    @FXML
    private ComboBox cbxEstado;
    @FXML
    private ComboBox cbxCidade;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtCEP;
     @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnLimpar;
    @FXML
    private StackPane cena;

    
    @FXML
    public void limpar(){ 
        txtNomeFantasia.setText("");
        txtTelefone.setText("");
        txtTipoDeProduto.setText("");
        txtRazaoSocial.setText("");
        txtEmail.setText("");
        txtCNPJ.setText("");
        cbxCidade.getSelectionModel().selectFirst();
        txtBairro.setText("");
        txtRua.setText("");
        txtNumero.setText("");
        txtCEP.setText("");
    }
    
     @FXML
    public void fechar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList items = FXCollections.observableArrayList();
        items.add("Ji-Paraná");
        items.add("Vilhena");
        items.add("Cacoal");
        items.add("Jaru");
        items.add("Ouro Preto do Oeste");
        items.add("Ariquemes");
        items.add("Porto Velho");
        items.add("Colorado do Oeste");
        items.add("Presidente Medici");
        items.add("Guajará Mirim");
        cbxCidade.getItems().addAll(items);
        
        ObservableList itemsEst = FXCollections.observableArrayList();
        itemsEst.add("Rondônia");
        cbxEstado.getItems().addAll(itemsEst);
    }    

    @FXML
    private void cadastrar(ActionEvent event) throws IOException {
        String mensagem = "";
        if(txtNomeFantasia.getText().equals("")){
           mensagem += "\n - Campo nome fantasia deve ser preenchido";
        }
        if(txtTelefone.getText().equals("")) {
          mensagem += "\n - Campo telefone deve ser preenchido";
        }
        if (txtTipoDeProduto.getText().equals("")) {               
         mensagem += "\n - Campo tipo de produto deve ser preenchido";
        }
        if(txtRazaoSocial.getText().equals("")){
        mensagem += "\n - Campo razão social deve ser preenchido";
        }
        if(txtEmail.getText().equals("")){
        mensagem += "\n - Campo email deve ser preenchido";
        }
        if(txtCNPJ.getText().equals("")){
         mensagem += "\n - Campo CNPJ deve ser preenchido";
        }
        if(cbxEstado.getPromptText().equals("")){
        mensagem += "\n - Campo estado deve ser preenchido";
        }
        if(cbxCidade.getPromptText().equals("")){
        mensagem += "\n - Campo cidade deve ser preenchido";
        }
        if(txtBairro.getText().equals("")){
        mensagem += "\n - Campo bairro deve ser preenchido";
        }
        if(txtRua.getText().equals("")){
        mensagem += "\n - Campo rua deve ser preenchido";
        }
        if(txtNumero.getText().equals("")){
        mensagem += "\n - Campo numero deve ser preenchido";
        }
        if(txtCEP.getText().equals("")){
        mensagem += "\n - Campo CEP deve ser preenchido";
        }
        if (!mensagem.equals("")) {
            abrirTelaErro(mensagem);
        }
        else{
            try {
                EntityManagerFactory emf= Persistence.createEntityManagerFactory("projetosistemaDS");
                EntityManager em =  emf.createEntityManager();

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setTxtNomeFantasia(txtNomeFantasia.getText());
                fornecedor.setTxtTelefone(txtTelefone.getText());
                fornecedor.setTxtTipoDeProduto(txtTipoDeProduto.getText());
                fornecedor.setTxtRazaoSocial(txtRazaoSocial.getText());
                fornecedor.setTxtEmail(txtEmail.getText());
                fornecedor.setTxtCNPJ(txtCNPJ.getText());
                fornecedor.setCbxEstado((String) cbxEstado.getSelectionModel().getSelectedItem());
                fornecedor.setCbxCidade((String) cbxCidade.getSelectionModel().getSelectedItem());
                fornecedor.setTxtBairro(txtBairro.getText());
                fornecedor.setTxtRua(txtRua.getText());
                fornecedor.setTxtNumero(txtNumero.getText());
                fornecedor.setTxtCEP(txtCEP.getText());

                em.getTransaction().begin();
                em.persist(fornecedor);
                em.getTransaction().commit();
            limpar();
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println(e.getStackTrace().length);
                System.out.println(e.getMessage());
            }
            fechar();
        }
        
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
    private void fornecedor (ActionEvent event) throws IOException {
    }
    
}
