    package br.edu.ifro.control;

    import br.edu.ifro.Modelo.Cliente;
    import br.edu.ifro.Modelo.Compra;
    import br.edu.ifro.Modelo.CompraProduto;
    import br.edu.ifro.Modelo.Fornecedor;
    import br.edu.ifro.Modelo.Produto;
    import com.jfoenix.controls.JFXButton;
    import com.jfoenix.controls.JFXComboBox;
    import com.jfoenix.controls.JFXDatePicker;
    import com.jfoenix.controls.JFXDialog;
    import com.jfoenix.controls.JFXDialogLayout;
    import com.jfoenix.controls.JFXTextField;
    import com.jfoenix.controls.JFXTreeTableColumn;
    import com.jfoenix.controls.JFXTreeTableView;
    import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
    import java.io.IOException;
    import java.net.URL;
    import java.time.LocalDate;
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
    import javafx.scene.control.TableView;
    import javafx.scene.control.TextField;
    import javafx.scene.control.TreeItem;
    import javafx.scene.control.TreeTableColumn;
    import javafx.scene.control.cell.TreeItemPropertyValueFactory;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;
    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.JoinColumn;
    import javax.persistence.JoinTable;
    import javax.persistence.ManyToMany;
    import javax.persistence.Persistence;
    import javax.persistence.Query;
    import javafx.scene.control.TreeTableView;
    import com.jfoenix.controls.RecursiveTreeItem;
    import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
    import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
    import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.time.Instant;
import java.time.ZoneId;
    import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
    import java.util.ArrayList;
import java.util.Date;
    import java.util.List;
    import java.util.ResourceBundle;
    import javafx.beans.property.SimpleStringProperty;
    import javafx.beans.property.StringProperty;
    import javafx.scene.Node;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.StackPane;
    import javafx.util.Callback;


    public class CompraController implements Initializable {
        
        @FXML
        private JFXTextField txtCodigo;
        @FXML
        private Text txtValorTotal;
        @FXML
        private JFXComboBox<?> cbxFormaPagamento;
        @FXML
        private JFXTextField txtValorLiquido;
        @FXML
        private JFXTextField txtValorBruto;
        @FXML
        private JFXTextField txtDesconto;
        @FXML
        private JFXTextField txtJuros;
        @FXML
        private JFXButton btnCalcular;
        @FXML
        private JFXButton btnCadastrar;
        @FXML
        private JFXButton btnCancelar;
        @FXML
        private JFXButton btnLimpar;
        @FXML
        private DatePicker dateCompra;
        @FXML
        private JFXButton btnCadastrarProduto;
        @FXML
        private JFXTextField txtNomeProduto;
        @FXML
        private JFXTextField txtQuantProduto;
        @FXML
        private JFXButton btnInserirProduto;
        @FXML
        private TableView tbCompras;
        @FXML
        private StackPane cena;
        private ArrayList<Produto> produtos = new ArrayList();
        @FXML
        private ComboBox<?> cbAlunos;
    
        @FXML
        public void limpar(){ 
            txtValorTotal.setText("");
            txtValorLiquido.setText("");
            txtValorBruto.setText("");
            txtDesconto.setText("");
            txtJuros.setText("");
            txtNomeProduto.setText("");
            txtQuantProduto.setText("");
            dateCompra.setValue(LocalDate.now());
        }

        @FXML
        public void fechar(){
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        }

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
            cbxFormaPagamento.getItems().addAll(items);
        }    
        

        @FXML
        private void cadastrarProduto(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/Produto.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),600,589);
            Stage stage = new Stage();
            stage.setTitle("cadastrar Produto");
            stage.setScene(scene);
            stage.show();
        }
        
        @FXML
        private void cadastrar(ActionEvent event) throws IOException {
            String mensagem = ""; 
            if(dateCompra.getValue() == null){
                mensagem += "\n - Campo data da compra deve ser preenchido";
            }
            if(cbxFormaPagamento.getPromptText().equals("")){
                mensagem += "\n - Campo forma de pagamento deve ser preenchido";
            }
            if (txtJuros.getText().equals("")) {               
                mensagem += "\n - Campo juros deve ser preenchido";
            }
            if(txtDesconto.getText().equals("")){
                mensagem += "\n - Campo desconto deve ser preenchido";
            }
            if(txtValorBruto.getText().equals("")){
                mensagem += "\n - Campo valor Bruto deve ser preenchido";
            }
            if(txtValorLiquido.getText().equals("")){
                mensagem += "\n - Campo valor liquido deve ser preenchido";
            }
            if(txtValorTotal.getText().equals("")){
                mensagem += "\n - Campo valor total deve ser preenchido";
            }
            if (!mensagem.equals("")) {
                abrirTelaErro(mensagem);
            }
            else{
                try {
                EntityManagerFactory emf= Persistence.createEntityManagerFactory("projetosistemaDS");
                EntityManager em =  emf.createEntityManager();

                Compra compra = new Compra();
                compra.setTxtValorTotal(txtValorTotal.getText());
                compra.setCbxFormaPagamento((String) cbxFormaPagamento.getSelectionModel().getSelectedItem());
                compra.setTxtValorLiquido(txtValorLiquido.getText());
                compra.setTxtValorBruto(txtValorBruto.getText());
                compra.setTxtDesconto(txtDesconto.getText());
                compra.setTxtJuros(txtJuros.getText());
                
                LocalDate localDate = dateCompra.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                compra.setDateCompra(Date.from(instant)); 
                
                compra.setTxtNomeProduto(txtNomeProduto.getText());
                compra.setTxtQuantProduto(txtQuantProduto.getText());
                
                em.getTransaction().begin();
                em.persist(compra);
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

        @FXML
        private void calcular(ActionEvent event) {
            double calc, quant, val,jur,desc,aux,aux1;

            quant = Double.parseDouble(txtQuantProduto.getText());
            val = Double.parseDouble(txtValorBruto.getText());
            jur= Double.parseDouble(txtJuros.getText());
            desc= Double.parseDouble(txtDesconto.getText());


            if(jur==0 && desc==0){
            calc = quant*val;
            txtValorBruto.setText(""+calc);
            txtValorTotal.setText(""+calc);
            txtValorLiquido.setText(""+calc);
            }
            else if(jur==0 && desc!=0){
            calc = quant*val;
            aux= calc-((calc*desc)/100);
            txtValorBruto.setText(""+calc);
            txtValorTotal.setText(""+aux);
            txtValorLiquido.setText(""+aux);
            }
            else if(desc==0 && jur!=0){
            calc = quant*val;
            aux=((calc*jur)/100)+ calc;
            txtValorBruto.setText(""+calc);
            txtValorTotal.setText(""+aux);
            txtValorLiquido.setText(""+aux);
            }
           else{
             calc = quant*val;
            aux=((calc*desc)/100)+ calc;
            aux1=aux -((aux*jur)/100);
            txtValorBruto.setText(""+calc);
            txtValorTotal.setText(""+aux1);
            txtValorLiquido.setText(""+aux1);
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
        } /* */

        @FXML
        private void inserirProduto(ActionEvent event) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetosistemaDS");
            EntityManager em = emf.createEntityManager();
            
            Query query = em.createQuery("SELECT a FROM Produto as a WHERE a.txtNome LIKE :nome OR a.txtCodigo = :codigo");
            query.setParameter("nome", "%"+txtNomeProduto.getText()+"%");
            query.setParameter("codigo", Integer.parseInt(txtNomeProduto.getText()));
            query.setMaxResults(1);
            
            if (query.getResultList().isEmpty()) {
            } else {
                Produto p = (Produto) query.getSingleResult();
                CompraProduto c = new CompraProduto();
                c.setProduto(p);
                c.setValor(p.getTxtCusto());
                c.setQuantidade(Integer.parseInt(txtQuantProduto.getText()));
                
                ObservableList<CompraProduto> produtos = tbCompras.getItems();
                produtos.add(c);
                tbCompras.setItems(produtos);
            }
        }

        private static final class Prod extends RecursiveTreeObject<Prod> {
            final StringProperty txtNome;

            Prod(String txtNome) {
                this.txtNome = new SimpleStringProperty(txtNome);
            }
        } 
    }
