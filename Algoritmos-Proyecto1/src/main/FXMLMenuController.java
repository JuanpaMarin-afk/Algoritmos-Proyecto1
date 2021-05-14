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

    //GridPane de Reistrar User
    @FXML
    private GridPane gridPaneRegister;
    //ComboBox para definir si es usuario o estudiante
    @FXML
    private ComboBox comboBoxRegister;
    @FXML
    private Button btnRegister;

    //TextFiel de registrar nombre y contraseña
    @FXML
    private TextField txtFielName;
    @FXML
    private TextField txtFielPasword;
    //***********************************************

    //Labels de los componentes de registrar
    @FXML
    private Label labelNameRegister;
    @FXML
    private Label labelProfileRegister;
    @FXML
    private Label labelPasswordRegister;

    //Tabla que muestra los usuarios y sus perfiles dentro de la universidad
    @FXML
    private TableView<?> tableDisplayUser;

    //Botones de login, exit y registrar
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnExit;

    //MenuItem User
    @FXML
    private MenuItem menuUserAdd;
    @FXML
    private MenuItem menuUserDisplay;
    @FXML
    private MenuItem menuUserSearch;
    @FXML
    private MenuItem menuUserRemove;

    @FXML
    private MenuItem menuCareerRemove;
    @FXML
    private MenuItem menuStudentRemove;

    @Override
    public void initialize(URL url, ResourceBundle rb) {//Tipo constructor

        //Link donde saque como funciona el comboBox https://www.youtube.com/watch?v=rKv8eavrAio
        //funcion del comboBox
        ObservableList<String> list = FXCollections.observableArrayList("Student", "Administrator");
        //Le agrega los dos items al combo antes definidos
        comboBoxRegister.setItems(list);
    }

    //Metodo que le da acción al comboBox
    @FXML
    private void selectComboBoxRegister(ActionEvent event) {
    }

    @FXML
    private void menuUserAdd(ActionEvent event) {
        this.gridPaneRegister.setVisible(true);
        this.btnRegister.setVisible(true);
    }

    @FXML
    private void btnLogin(ActionEvent event) {
    }

    @FXML
    private void btnExit(ActionEvent event) {
    }

    @FXML
    private void btnRegister(ActionEvent event) {
    }

}//end class
