package br.edu.ifro.control;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JMenuItem;

public class MenuPrincipalController implements Initializable {

    @FXML
    private MenuItem cadCliente;
    @FXML
    private MenuItem cadFuncionario;
    @FXML
    private MenuItem cadFornecedor;
    @FXML
    private MenuItem cadProduto;
    @FXML
    private MenuItem cadCompra;
    @FXML
    private MenuItem inserirVenda;
    @FXML
    private ImageView btnFechar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
    }
    private void ClienteButtonAction(ActionEvent event)throws IOException{
       abrirTela("view/Cliente.fxml", "Cadastrar Cliente",900,682);
    }    
   
    private void ProdutoButtonAction(ActionEvent event) throws IOException{
        abrirTela("view/Produto.fxml","Cadastrar Produto",718,538);   
    } 
   
    @FXML
    private void chamarCliente(ActionEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Cliente.fxml","Cadastrar Cliente",900,682);
    }

    @FXML
    private void chamarFunc(ActionEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Funcionario.fxml","Cadastrar Funcionário",895,654);
    }

    @FXML
    private void chamarForn(ActionEvent event) throws IOException {
        abrirTela("/br/edu/ifro/view/Fornecedor.fxml", "Cadastrar Fornecedor", 888,702);
    }
    
    @FXML
    private void chamarProd(ActionEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Produto.fxml","Cadastrar Produto",600,589);
    }

    @FXML
    private void chamarCompra(ActionEvent event) throws IOException{
     abrirTela("/br/edu/ifro/view/Compra.fxml","Cadastrar Compra",751,724);
    }
    
    @FXML
    private void chamarVenda(ActionEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Venda.fxml","Vender Produto",624,739);
    } 
    
    @FXML 
    private void chamarRec(ActionEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Recebimento.fxml","Recebimento",769,575);
    }

    @FXML 
    private void chamarPag(ActionEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Pagamento.fxml","Pagamento",802,609);
    }
    
    @FXML
    private void chamarCliente2(MouseEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Cliente.fxml","Cadastrar Cliente",900,682);
    }

    @FXML
    private void chamarFunc2(MouseEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Funcionario.fxml","Cadastrar Funcionário",895,618);
    }

    @FXML
    private void chamarForn2(MouseEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Fornecedor.fxml","Cadastrar Fornecedor",888,702);
    }

    @FXML
    private void chamarProd2(MouseEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Produto.fxml","Cadastrar Produto",600,589);
    }

    @FXML
    private void chamarCompra2(MouseEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Compra.fxml","Cadastrar Compra",751,724);
    }

    @FXML
    private void chamarVenda2(MouseEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Venda.fxml","Vender Produto",624,739);
    }

    @FXML
    private void chamarRec2(MouseEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Recebimento.fxml","Recebimento",769,575);
    }

    @FXML
    private void chamarPag2(MouseEvent event) throws IOException {
     abrirTela("/br/edu/ifro/view/Pagamento.fxml","Pagamento",802,609);
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

    @FXML
    private void fecharPrograma(MouseEvent event) {
        System.exit(0);
    }
}