package main;

import domain.CircularDoublyLinkedList;
import domain.DoublyLinkedList;
import domain.Security;
import domain.SinglyLinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author juanp
 */
public class FXMLMenuController implements Initializable {

    //Atributos Aqui poner los atributos de la clase, tambien todo lo de la interfaz :D, porfa poner comentarios para ver cuales son cada una de ellas
    CircularDoublyLinkedList loginList = new CircularDoublyLinkedList(); //Lista para el modulo de seguridad

    DoublyLinkedList carreerList = new DoublyLinkedList();//Lista para el modulo de las carreras

    SinglyLinkedList studentList = new SinglyLinkedList();//Lista para el modulo de estudiantes

    CircularDoublyLinkedList courseList = new CircularDoublyLinkedList();//Lista para el modulo de cursos

    SinglyLinkedList sheduleList = new SinglyLinkedList();//Lista para el modulo de horarios

    CircularDoublyLinkedList enrollmentList = new CircularDoublyLinkedList();//Lista para el modulo de matricula

    //TextFields para poder iniciar sesion
    @FXML
    private TextField textUser;
    @FXML
    private PasswordField textPassword;

    //Botones 
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnClean;

    //**************************  MenuItemUser   **************************
    //GridPane de Reistrar User
    @FXML
    private GridPane gridUser;

    //Boton de registrar usuario
    @FXML
    private Button btnRegisterUser;

    //ComboBox para definir si es administrador o estudiante
    @FXML
    private ComboBox comboBoxUser;

    //TextFiel de registrar nombre y contraseña
    @FXML
    private TextField txtFielName;
    @FXML
    private TextField txtFielPasword;

    //Menus Items de User
    @FXML
    private MenuItem menuUserAdd;
    @FXML
    private MenuItem menuUserDisplay;
    @FXML
    private MenuItem menuUserRemove;
    @FXML
    private MenuItem menuUserModify;

    //Tabla de User
    @FXML
    private TableView<?> tableUser;

    //**************************  fin MenuItemUser   **************************
    @Override
    public void initialize(URL url, ResourceBundle rb) {//Tipo constructor

        //Link donde saque como funciona el comboBox https://www.youtube.com/watch?v=rKv8eavrAio
        //funcion del comboBox
        ObservableList<String> list = FXCollections.observableArrayList("Student", "Administrator");
        //Le agrega los dos items al combo antes definidos
        comboBoxUser.setItems(list);
    }

    //************************** USER **************************
    @FXML
    private void menuUserAdd(ActionEvent event) {
        this.gridUser.setVisible(true);
        this.btnRegisterUser.setVisible(true);
    }
    
    @FXML
    private void menuUserDisplay(ActionEvent event) {
    }
    
    @FXML
    private void menuUserRemove(ActionEvent event) {
    }
    
    @FXML
    private void menuUserModify(ActionEvent event) {
    }
    
    @FXML
    private void comboBoxUser(ActionEvent event) {
    }
    
    @FXML
    private void btnRegisterUser(ActionEvent event) {
        try {
            if (!this.txtFielName.getText().equals("") && !this.txtFielPasword.getText().equals("")) {
                Security security = new Security(this.comboBoxUser.getValue().toString(), this.txtFielName.getText(), this.txtFielPasword.getText());
                

                    if (!loginList.contains(security) || loginList.isEmpty()) {//Si no contiene al user, agregarlo //
                        loginList.add(security);
                        System.out.println(loginList.toString());
                        
                    } else {
                        
                        System.out.println("Lista ya ocntiene un usuario con el mismo nombre");
                        
                    }
                
                
            }
        } catch (Exception e) {
            
        }
    }
    //************************** fin USER **************************

    @FXML
    private void btnLogin(ActionEvent event) {//Inicia Sesion

    }
    
    @FXML
    private void btnExit(ActionEvent event) {//Cierra Sesion

    }
    
    @FXML
    private void btnClean(ActionEvent event) {//Limpia la pantalla
        //User
        this.gridUser.setVisible(false);
        this.btnRegisterUser.setVisible(false);
        
    }
    
}//end class
