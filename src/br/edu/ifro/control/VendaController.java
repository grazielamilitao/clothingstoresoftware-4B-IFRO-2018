package br.edu.ifro.control;
import br.edu.ifro.Modelo.CompraProduto;
import br.edu.ifro.Modelo.Funcionario;
import br.edu.ifro.Modelo.Produto;
import br.edu.ifro.Modelo.Venda;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import br.edu.ifro.Modelo.VendaProduto;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.TableView;


public class VendaController implements Initializable {

    @FXML
    private DatePicker DataVenda;
    @FXML
    private JFXTextField txtDesconto;
    @FXML
    private JFXTextField txtValorPago;
    @FXML
    private JFXTextField txtJuros;
    @FXML
    private JFXTextField txtQtd;
    @FXML
    private Text txtValorTotal;
    @FXML
    private JFXButton btnFinalizar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnLimpar;
    @FXML
    private StackPane cena;
    @FXML
    private JFXTextField txtProduto;
    @FXML
    private JFXButton btnInserirProduto;
    private ArrayList<Produto> produtos = new ArrayList();
    @FXML
    private JFXComboBox<?> cbxFormaPagamento;
    @FXML
    private TableView tbVenda;
    @FXML
    private JFXButton btnCalcular;
    
    @FXML
    private void Finalizar(ActionEvent event) throws IOException {
        String mensagem = "";
        if(txtProduto.getText().equals("")){
         mensagem += "\n -Campo descrição deve ser preenchido";
        }
         if(txtQtd.getText().equals("")) {
         mensagem += "\n -Campo quantidade deve ser preenchido";
        }
         if(DataVenda.getValue() == null){
         mensagem += "\n -Campo data deve ser preenchido";
        }
         if (txtDesconto.getText().equals("")) {               
         mensagem += "\n -Campo desconto deve ser preenchido";
        }
         if(txtValorPago.getText().equals("")){
         mensagem += "\n -Campo valor pago deve ser preenchido";
        }
         if(txtJuros.getText().equals("")){
        mensagem += "\n -Campo valor juros deve ser preenchido";
        }
         if(txtValorTotal.getText().equals("")){
        mensagem += "\n -Campo valor total deve ser preenchido";
        }
        if (!mensagem.equals("")) {
            abrirTelaErro(mensagem);
        }
        else{
          try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("projetosistemaDS");
            EntityManager em =  emf.createEntityManager();

            Venda venda = new Venda();
            
            LocalDate localDate = DataVenda.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            venda.setDataVenda(Date.from(instant)); 
            
            venda.setDesconto(txtDesconto.getText());
            venda.setValorPago(txtValorPago.getText());
            venda.setProduto(txtProduto.getText());
            venda.setJuros(txtJuros.getText());
            venda.setQtd(txtQtd.getText());
            venda.setFormapagamento(cbxFormaPagamento.getPromptText());
            venda.setQtd(txtValorTotal.getText());

            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();

            Limpar(event);
            DataVenda.setValue(LocalDate.now());
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
        txtProduto.setText("");
        txtQtd.setText("");
        txtValorPago.setText("");
        txtJuros.setText("");
        txtDesconto.setText("");
        txtValorTotal.setText("");
        DataVenda.setValue(LocalDate.now());
    }
 
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList items = FXCollections.observableArrayList();
        items.add("A vista");
        items.add("Parcelado em 1x no cartão");
        items.add("Parcelado em 2x no cartão");
        items.add("Parcelado em 3x no cartão");
        items.add("Parcelado em 4x no cartão");
        items.add("Parcelado em 5x no cartão");
        items.add("Parcelado em 6x no cartão");
        cbxFormaPagamento.getItems().addAll(items);
        
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
    private void inserirProduto (ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetosistemaDS");
        EntityManager em = emf.createEntityManager();
            
        Query query = em.createQuery("SELECT a FROM Produto as a WHERE a.txtNome LIKE :nome OR a.txtCodigo = :codigo");
        query.setParameter("nome", "%"+txtProduto.getText()+"%");
        query.setParameter("codigo", Integer.parseInt(txtProduto.getText()));
        query.setMaxResults(1);
            
            if (query.getResultList().isEmpty()) {
            } else {
                Produto p = (Produto) query.getSingleResult();
                VendaProduto v = new VendaProduto();
                v.setProduto(p);
                v.setQuantidade(Integer.parseInt(txtQtd.getText()));
                v.setValor(p.getTxtCusto());
                
                ObservableList<VendaProduto> produtos = tbVenda.getItems();
                produtos.add(v);
                tbVenda.setItems(produtos);
            }
    }

    @FXML
    private void calcular(ActionEvent event) {
        double calc, quant, val,jur,desc,aux,aux1;

        quant = Double.parseDouble(txtQtd.getText());
        val = Double.parseDouble(txtValorPago.getText());
        jur= Double.parseDouble(txtJuros.getText());
        desc= Double.parseDouble(txtDesconto.getText());


        if(jur==0 && desc==0){
        calc = quant*val;
        txtValorTotal.setText(""+calc);
        }
        else if(jur==0 && desc!=0){
        calc = quant*val;
        aux= calc-((calc*desc)/100);
        txtValorTotal.setText(""+aux);
        }
       else if(desc==0 && jur!=0){
        calc = quant*val;
        aux=((calc*jur)/100)+ calc;
        txtValorTotal.setText(""+aux);
      }
       else{
        calc = quant*val;
        aux=((calc*desc)/100)+ calc;
        aux1=aux -((aux*jur)/100);
        txtValorTotal.setText(""+aux1);
        }  

    }
    
}
