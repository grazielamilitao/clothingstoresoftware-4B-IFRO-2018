
package br.edu.ifro.control;

import br.edu.ifro.Modelo.Cliente;
import br.edu.ifro.Modelo.Conta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ContaController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private StackPane cena;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void cadastrar(ActionEvent event) throws IOException {
        String mensagem = "";
        if (txtDescricao.getText().equals("")){
            mensagem += "\n - O campo descrição deve ser preenchido.";
        }
        if(txtNome.getText().equals("")){
            mensagem += "\n - Campo nome deve ser preenchido.";
        }
        
        if (!mensagem.equals("")) {
            abrirTelaErro(mensagem);
        }
        else{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetosistemaDS");
            EntityManager em = emf.createEntityManager();

            Conta cont = new Conta();
            cont.setTxtNome (txtNome.getText());
            cont.setTxtDescricao(txtDescricao.getText());
            
        em.getTransaction().begin();
        em.persist(cont);
        em.getTransaction().commit();
        }
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
    
}
