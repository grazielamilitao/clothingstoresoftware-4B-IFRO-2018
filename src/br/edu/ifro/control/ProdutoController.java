
package br.edu.ifro.control;

import br.edu.ifro.Modelo.Produto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ProdutoController implements Initializable {

    @FXML
    private JFXTextField txtTamanho;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtMarca;
    @FXML
    private JFXTextField txtEstoque;
    @FXML
    private JFXComboBox<?> cbxFornecedor;
    @FXML
    private JFXTextField txtCusto;
    @FXML
    private JFXTextField txtVarejo;
    @FXML
    private JFXTextField txtAtacado;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnLimpar;
    @FXML
    private JFXButton btnCandastrar;
    @FXML
    private StackPane cena;
 
    @FXML
    private void cadastrar(ActionEvent event) throws IOException {
        String mensagem = "";
         if(txtNome.getText().equals("")){
          mensagem += "\n -Campo nome deve ser preenchido";
        }
         if(txtTamanho.getText().equals("")) {
           mensagem += "\n -Campo tamanho deve ser preenchido";
        }
         if (txtDescricao.getText().equals("")) {               
          mensagem += "\n -Campo descrição deve ser preenchido";
        }
         if(txtMarca.getText().equals("")){
            mensagem += "\n -Campo marca deve ser preenchido";
        }
          if(cbxFornecedor.getPromptText().equals("")){
          mensagem += "\n -Campo fornecedor deve ser preenchido";
        }
         if(txtEstoque.getText().equals("")){
           mensagem += "\n -Campo estoque deve ser preenchido";
        }
         if(txtCusto.getText().equals("")){
           mensagem += "\n -Campo custo deve ser preenchido";
        }
          if(txtVarejo.getText().equals("")){
            mensagem += "\n -Campo varejo deve ser preenchido";
        }
         if(txtAtacado.getText().equals("")){
           mensagem += "\n -Campo atacado deve ser preenchido";
        }
          if (!mensagem.equals("")) {
            abrirTelaErro(mensagem);
        }
        else{
            try {
                EntityManagerFactory emf= Persistence.createEntityManagerFactory("projetosistemaDS");
                EntityManager em =  emf.createEntityManager();

                Produto produto = new Produto();
                produto.setTxtTamanho(txtTamanho.getText());
                produto.setTxtDescricao(txtDescricao.getText());
                produto.setTxtEstoque(Integer.parseInt(txtEstoque.getText()));
                produto.setTxtNome(txtNome.getText());
                produto.setTxtMarca(txtMarca.getText());
                produto.setCbxFornecedor((String) cbxFornecedor.getSelectionModel().getSelectedItem());
                produto.setTxtCusto(Double.parseDouble(txtCusto.getText()));
                produto.setTxtVarejo(Double.parseDouble(txtVarejo.getText()));
                produto.setTxtAtacado(Double.parseDouble(txtAtacado.getText()));

                em.getTransaction().begin();
                em.persist(produto);
                em.getTransaction().commit();

                txtTamanho.setText("");
                txtNome.setText("");
                txtDescricao.setText("");
                txtMarca.setText("");
                txtEstoque.setText("");
                txtCusto.setText("");
                txtVarejo.setText("");
                txtAtacado.setText("");
            limpar();
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println(e.getStackTrace().length);
            }
            cancelar();
        }
    }
    @FXML
    private void limpar() {
        txtTamanho.setText("");
        txtNome.setText("");
        txtDescricao.setText("");
        txtMarca.setText("");
        txtEstoque.setText("");
        txtCusto.setText("");
        txtVarejo.setText("");
        txtAtacado.setText("");
        
    }
    @FXML
    private void cancelar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        ObservableList items = FXCollections.observableArrayList();
        items.add("Colcci");
        items.add("Curitiba Calçados e Confecções");
        items.add("Lança Perfume");
        items.add("Yexx");
        items.add("All star");
        items.add("Zary");
        items.add("Hering");
        items.add("Belíssima");
        items.add("Patrícia Gampires");
        items.add("Dafiti");
        cbxFornecedor.getItems().addAll(items);
        
    }    

    @FXML
    private void cadastrarFornecedor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/Fornecedor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),888,702);
        Stage stage = new Stage();
        stage.setTitle("cadastrar Fornecedor");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
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
    private void produto (ActionEvent event) throws IOException { 
    
}
}
