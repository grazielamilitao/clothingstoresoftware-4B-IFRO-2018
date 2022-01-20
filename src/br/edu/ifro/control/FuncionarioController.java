package br.edu.ifro.control;

import br.edu.ifro.Modelo.Funcionario;
import br.edu.ifro.Modelo.Produto;
import com.google.common.hash.Hashing;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author 04226528220
 */
public class FuncionarioController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtRg;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtSalario;
    @FXML
    private RadioButton rFem;
    @FXML
    private RadioButton rMasc;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<?> cbxCargo;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnLimpar;
    @FXML
    private DatePicker dataNascimento;
    @FXML
    private ComboBox<?> cbxCidade;
    @FXML
    private ComboBox<?> cbxEstado;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField txtLogin;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private StackPane cena;

  
    @FXML
    private void Cadastrar(ActionEvent event) throws IOException {
        String mensagem = "";
        if(txtNome.getText().equals("")){
            mensagem += "\n - Campo nome deve ser preenchido.";
        }
        if (txtCpf.getText().equals("")) {               
            mensagem += "\n - Campo CPF deve ser preenchido.";
        }
        if(txtSalario.getText().equals("")){
            mensagem += "\n - Campo Salario  deve ser preenchido.";
        }
        if((txtTelefone.getText().equals("")) && (txtCelular.getText().equals(""))){
            mensagem += "\n - Campo Telefone ou Celular deve ser preenchido.";
        }
        if(dataNascimento.getValue() == null){
            mensagem += "\n - Campo Data de Nascimento deve ser preenchido";
        }
        if(txtRg.getText().equals("")){
            mensagem += "\n - Campo RG deve ser preenchido.";
        }
        if(txtBairro.getText().equals("")){
            mensagem += "\n - Campo Bairro deve ser preenchido.";
        }
        if(txtRua.getText().equals("")){
            mensagem += "\n - Campo Rua deve ser preenchido.";
        }
        if(txtNumero.getText().equals("")){
            mensagem += "\n - Campo numero da casa deve ser preenchido.";
        }
        if(txtCep.getText().equals("")){
            mensagem += "\n - Campo CEP deve ser preenchido.";
        }
        if (sexo.getSelectedToggle() == null){
            mensagem += "\n - O campo sexo não esta selecinado.";
        }
        if(txtEmail.getText().equals("")){
            mensagem += "\n - Campo Email deve ser preenchido.";
        }
        if(cbxCargo.getPromptText().equals("")){
            mensagem += "\n - Campo cargo deve ser selecinado.";
        }
        if (!mensagem.equals("")) {
            abrirTelaErro(mensagem);
        }
        else{
            try {
                EntityManagerFactory emf= Persistence.createEntityManagerFactory("projetosistemaDS");
                EntityManager em =  emf.createEntityManager();
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(txtNome.getText());
                funcionario.setCpf(txtCpf.getText()); 
                funcionario.setRg(txtRg.getText());
                funcionario.setTelefone(txtTelefone.getText());
                funcionario.setCelular(txtCelular.getText());
                funcionario.setEmail(txtEmail.getText());
                funcionario.setRua(txtRua.getText());
                funcionario.setBairro(txtBairro.getText());
                funcionario.setCep(txtCep.getText());
                funcionario.setNumero(txtNumero.getText());
                funcionario.setSalario(txtSalario.getText());
                funcionario.setLogin(txtLogin.getText());
                String hashed = Hashing.sha256().hashString(txtSenha.getText(), StandardCharsets.UTF_8).toString();
                funcionario.setSenha(hashed);
                funcionario.setDataNascimento(dataNascimento.getValue().toString());System.out.println(dataNascimento.getValue().toString());
                funcionario.setCargo((String) cbxCargo.getSelectionModel().getSelectedItem());
                funcionario.setEstado((String) cbxEstado.getSelectionModel().getSelectedItem());
                funcionario.setCidade((String) cbxCidade.getSelectionModel().getSelectedItem());
                RadioButton selectedRadioButton = (RadioButton) sexo.getSelectedToggle();
                String sexoSelecionado = selectedRadioButton.getText();
                funcionario.setSexo(sexoSelecionado);
                em.getTransaction().begin();
                em.persist(funcionario);
                em.getTransaction().commit();
             
            Limpar(event);
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println(e.getStackTrace().length);
                System.out.println(e.getMessage());
            }
            Cancelar(event);
        }
    }

    @FXML
    private void Cancelar(ActionEvent event) {
    Stage stage = (Stage) btnCancelar.getScene().getWindow();
    stage.close();
    }

    @FXML
    private void Limpar(ActionEvent event) {  
        txtNome.setText("");
        txtCpf.setText("");
        txtRg.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        dataNascimento.setValue(null);
        txtEmail.setText("");
        txtSalario.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        txtBairro.setText(""); 
        txtRua.setText("");
        txtNumero.setText(""); 
        txtCep.setText("");
    }
        
        @Override
        public void initialize(URL url, ResourceBundle rb) {
        ObservableList OutrosItens = FXCollections.observableArrayList();
        OutrosItens.add("Gerente");
        OutrosItens.add("Caixa");
        OutrosItens.add("Vendedor");
        OutrosItens.add("Proprietário");
        cbxCargo.getItems().addAll(OutrosItens);
        
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
    }
