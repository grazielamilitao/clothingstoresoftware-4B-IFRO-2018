    
package br.edu.ifro.control;

import br.edu.ifro.Modelo.Cliente;
import br.edu.ifro.Modelo.Funcionario;
import com.google.common.hash.Hashing;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ClienteController implements Initializable {

    @FXML
    private DatePicker dataNasc;
    @FXML
    private JFXTextField txtTelefone;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCPF;
    @FXML
    private JFXTextField txtDDD;
    @FXML
    private JFXTextField txtCelular;
    @FXML
    private JFXTextField txtRG;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXTextField txtRua;
    @FXML
    private JFXTextField txtNum;
    @FXML
    private JFXTextField txtCEP;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnLimpar;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXComboBox<?> cbxCidade;
    @FXML
    private JFXComboBox<?> cbxEstado;
    @FXML
    private JFXRadioButton rFem;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private JFXRadioButton rMasc;
    @FXML
    private StackPane cena;
    @FXML
    private AnchorPane painel;

    @FXML
    public void limpar(){  
        txtNome.setText("");
        txtCPF.setText("");
        txtRG.setText("");
        dataNasc.setValue(null);
        txtDDD.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
        txtRua.setText("");
        txtBairro.setText(""); 
        txtNum.setText(""); 
        txtCEP.setText("");
    }
    
    @FXML
    public void fechar(){
        Stage stage = (Stage ) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void cadastrar()throws IOException{
         String mensagem = "";  
        if(txtNome.getText().equals("")){
        mensagem += "\n - Campo nome deve ser preenchido";
        }
        if (txtCPF.getText().equals("")) {               
        mensagem += "\n - Campo CPF deve ser preenchido";
        }
        if(txtDDD.getText().equals("")){
        mensagem += "\n - Campo DDD deve ser preenchido";
        }
        if((txtTelefone.getText().equals("")) && (txtCelular.getText().equals(""))){
        mensagem += "\n - Campo Telefone ou Celular deve ser preenchido";
        }
        if(dataNasc.getValue() == null){
         mensagem += "\n - Campo Data de Nascimento deve ser preenchido";
        }
        if(txtRG.getText().equals("")){
         mensagem += "\n - Campo RG deve ser preenchido";
        }
        if(txtBairro.getText().equals("")){
        mensagem += "\n - Campo Bairro deve ser preenchido";
        }
        if(txtRua.getText().equals("")){
        mensagem += "\n - Campo Rua deve ser preenchido";
        }
        if(txtNum.getText().equals("")){
        mensagem += "\n - Campo numero da casa deve ser preenchido";
        }
        if(txtCEP.getText().equals("")){
        mensagem += "\n - Campo CEP deve ser preenchido";
        }
        if (!mensagem.equals("")) {
            abrirTelaErro(mensagem);
        }
        else{
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetosistemaDS");
                EntityManager em = emf.createEntityManager();

                Cliente c = new Cliente();
                c.setTxtNome (txtNome.getText());
                c.setTxtCPF(txtCPF.getText());
                c.setTxtRG (txtRG.getText());
                LocalDate nasc = dataNasc.getValue();
                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
                c.setDataNasc(nasc.format(formatters));
                c.setTxtDDD (txtDDD.getText());
                c.setTxtTelefone (txtTelefone.getText());
                c.setTxtCelular (txtCelular.getText());
                c.setTxtEmail (txtEmail.getText());
                RadioButton selectedRadioButton = (RadioButton) sexo.getSelectedToggle();
                String sexoSelecionado = selectedRadioButton.getText();
                c.setSexo(sexoSelecionado);
                c.setTxtRua (txtRua.getText());
                c.setTxtBairro (txtBairro.getText());
                c.setTxtNum (txtNum.getText());
                c.setTxtCEP (txtCEP.getText());
                c.setCbxCidade((String) cbxCidade.getSelectionModel().getSelectedItem()); 
                c.setCbxEstado((String) cbxEstado.getSelectionModel().getSelectedItem()); 

                em.getTransaction().begin();
                em.persist(c);
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList itemsCid = FXCollections.observableArrayList();
        itemsCid.add("Ji-Paraná");
        itemsCid.add("Vilhena");
        itemsCid.add("Cacoal");
        itemsCid.add("Jaru");
        itemsCid.add("Ouro Preto do Oeste");
        itemsCid.add("Ariquemes");
        itemsCid.add("Porto Velho");
        itemsCid.add("Colorado do Oeste");
        itemsCid.add("Presidente Medici");
        itemsCid.add("Guajará Mirim");
        cbxCidade.getItems().addAll(itemsCid);
        
        ObservableList itemsEst = FXCollections.observableArrayList();
        itemsEst.add("Rondônia");
        cbxEstado.getItems().addAll(itemsEst);
    }    

    @FXML
    private void ENTER(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            cadastrar();
        }
        else if(event.getCode() == KeyCode.ESCAPE){
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
    private void ESC(KeyEvent event) {
    }
    
}
