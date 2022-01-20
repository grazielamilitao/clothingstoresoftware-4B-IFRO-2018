package br.edu.ifro.control;
import br.edu.ifro.Modelo.Cliente;
import br.edu.ifro.Modelo.Funcionario;
import com.google.common.hash.Hashing;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {
    @FXML
    private JFXButton botentrar;
    @FXML
    private JFXPasswordField txtSenha;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private AnchorPane painel;
    @FXML
    private StackPane cena;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (verificaPrimerioCadastro()) {
            try {
             abrirTela("/br/edu/ifro/view/Funcionario.fxml","Cadastrar Funcionário",895,654);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    private boolean verificaPrimerioCadastro() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetosistemaDS");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT count(f) FROM Funcionario f");
        long quantidade = (long) query.getSingleResult();
        return quantidade == 0;
    }
    
    private void abrirMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/MenuPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1159,653);
        Stage stage = new Stage();
        stage.setTitle("Menu Principal");
        stage.setScene(scene);   
        stage.show();
       // abrirTela("/br/edu/ifro/view/MenuPrincipal.fxml","Menu Principal",1159,653);
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, new  EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent window){
                Stage stg = (Stage) botentrar.getScene().getWindow();
                stg.close();
            }
        });   
        
    }

    @FXML
    private void entrar(ActionEvent event) throws IOException {
       Funcionario usuario;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetosistemaDS");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT f FROM Funcionario f WHERE f.login=:user");
        query.setParameter("user", txtUsuario.getText());
        String hashed = Hashing.sha256().hashString(txtSenha.getText(), StandardCharsets.UTF_8).toString();
        //query.setParameter("senha", hashed);
        List<Funcionario> l =  query.getResultList();
        
        if(txtUsuario.getText().equals("") || txtSenha.getText().equals("")){
        abrirTelaErro("Campo usuario ou campo senha não esta preenchido devidamente");
        }
        else
        if (l.isEmpty()) {               
            abrirTelaErro("Usuario não encontrado");
        }
        else 
        if(l.get(0).getSenha().equals(hashed)){
             abrirMenu();
             Stage stg = (Stage) botentrar.getScene().getWindow();
             stg.close();
        }
        
        else{
            abrirTelaErro("senha incorreta, tente outra senha novamente");
        }
         em.close();
        emf.close();
        
        
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
    private void abrirTela(String caminho, String titulo, int wigth, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(caminho));
        Scene scene = new Scene(fxmlLoader.load(),wigth,height);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();  
    } 
}