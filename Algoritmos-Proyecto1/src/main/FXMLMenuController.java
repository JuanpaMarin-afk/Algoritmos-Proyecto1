package main;

import domain.CircularDoublyLinkedList;
import domain.DoublyLinkedList;
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
    
    //ComboBox para definir si es administrador o estudiante
    @FXML
    private ComboBox comboBoxUser;
    
    //Boton de registrar usuario
    @FXML
    private Button btnRegisterUser;
    
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
    
    private void btnRegister(ActionEvent event) {
        
            try {
                
            } catch (Exception e) {
                
            }
    }
    @FXML
    private void comboBoxUser(ActionEvent event) {
    }
    @FXML
    private void btnRegisterUser(ActionEvent event) {
    }
    //************************** fin USER **************************
    
    
    
    @FXML
    private void btnLogin(ActionEvent event) {
    }

    @FXML
    private void btnExit(ActionEvent event) {
    }

    @FXML
    private void btnClean(ActionEvent event) {
    }




}//end class
