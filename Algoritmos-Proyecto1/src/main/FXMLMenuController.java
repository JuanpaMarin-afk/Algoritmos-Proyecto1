package main;

import data.FileXML;
import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Security;
import domain.SinglyLinkedList;
import domain.Student;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author juanp
 */
public class FXMLMenuController implements Initializable {

    //Atributos Aqui poner los atributos de la clase, tambien todo lo de la interfaz :D, porfa poner comentarios para ver cuales son cada una de ellas
    CircularDoublyLinkedList loginList = new CircularDoublyLinkedList(); //Lista para el modulo de seguridad

    DoublyLinkedList careerList = new DoublyLinkedList();//Lista para el modulo de las carreras

    SinglyLinkedList studentList = new SinglyLinkedList();//Lista para el modulo de estudiantes

    CircularDoublyLinkedList courseList = new CircularDoublyLinkedList();//Lista para el modulo de cursos

    SinglyLinkedList sheduleList = new SinglyLinkedList();//Lista para el modulo de horarios

    CircularDoublyLinkedList enrollmentList = new CircularDoublyLinkedList();//Lista para el modulo de matricula

    Security user = new Security();//User que se usa para el inicio de sesion

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
    //**************************  XML Users   **************************
    FileXML xmlUser;
    String userAddress = "UserSystem.xml";

    //**************************  XML Careers   **************************
    FileXML xmlCareer;
    String careerAddress = "CareerSystem.xml";

    //**************************  FIN XML Users   **************************
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuItemReport;
    @FXML
    private Menu menuItemUser;
    @FXML
    private Menu menuItemCareers;
    @FXML
    private Menu menuItemStudent;
    @FXML
    private Menu menuItemCourse;
    @FXML
    private Menu menuItemShedule;
    @FXML
    private Menu menuItemInscription;

    //**************************  MenuItemCareer   **************************
    @FXML
    private MenuItem addCareer;
    @FXML
    private MenuItem addStudent;
    @FXML
    private GridPane gridCareer;
    @FXML
    private TextField txtFielId;
    @FXML
    private TextField txtFielDescription;
    @FXML
    private Button btnRegisterCareer;
    //**************************  fin MenuItemCareer   **************************
    @FXML
    private MenuItem modifyCareer;
    @FXML
    private MenuItem removeCareer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {//Tipo constructor
        //XML PARA USER
        xmlUser = new FileXML();
        if (!xmlUser.exist("UserSystem.xml")) {
            xmlUser.createXML("Users", userAddress, "UserSystem");
        } else {//Como ya se creo el xml, entonces se va a cargar la lista con los datos que tenga el xml
            loginList = xmlUser.readXMLUser(userAddress, "User");
        }

        //XML PARA CAREER
        xmlCareer = new FileXML();
        if (!xmlCareer.exist("CareerSystem.xml")) {
            xmlCareer.createXML("Careers", careerAddress, "CareerSystem");
        } else {//Como ya se creo el xml, entonces se va a cargar la lista con los datos que tenga el xml
            careerList = xmlCareer.readXMLCareer(careerAddress, "Career");
        }
    }

    //************************** USER **************************
    @FXML
    private void menuUserAdd(ActionEvent event) {
        btnClean(event);
        this.gridUser.setVisible(true);
        this.btnRegisterUser.setVisible(true);
    }

    @FXML
    private void btnRegisterUser(ActionEvent event) {
        boolean condition = false;
        try {
            if (!this.txtFielName.getText().equals("") && !this.txtFielPasword.getText().equals("")) {
                Security security = new Security("Administrator", this.txtFielName.getText(), this.txtFielPasword.getText());

                if (loginList.isEmpty()) {//Como es el primer valor lo agrega si o si
                    loginList.add(security);
                    xmlUser.writeXML(userAddress, "User", security.getDataName(), security.getData());
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("User successfully registered");
                    alert.showAndWait();
                    condition = true;
                }
                if (!loginList.contains(security)) {//Si no contiene al user, agregarlo //
                    loginList.add(security);
                    xmlUser.writeXML(userAddress, "User", security.getDataName(), security.getData());
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("User successfully registered");
                    alert.showAndWait();
                } else {

                    if (condition == false) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("There is already a user in the system");
                        alert.showAndWait();
                    }

                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("");
                alert.setContentText("Do you want to add another User?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    menuUserAdd(event);
                } else {
                    btnClean(event);
                }

            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("You need to fill all the empty field.");
                alert.showAndWait();
            }
        } catch (Exception e) {

        }
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

    //************************** fin USER **************************
    //************************** SEGURIDAD **************************
    @FXML
    private void btnLogin(ActionEvent event) {//Inicia Sesion
        try {
            user.setUser(this.textUser.getText());
            user.setPassword(this.textPassword.getText());
            user = xmlUser.readXMLLogIn(userAddress, "User", user);
            if (user.getType() == null) {//Si no hay ningun usuario registrado con esta informacion, muestra un mensaje al usuario
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("There is no user with this data");
                alert.showAndWait();
            } else {
                if (user.getType().equals("Administrator")) { //Administrador tiene acceso a toda la funcionalidad del sistema
                    this.menuBar.setDisable(false);
                    this.menuItemUser.setDisable(false);
                    this.menuItemCareers.setDisable(false);
                    this.menuItemStudent.setDisable(false);
                    this.menuItemCourse.setDisable(false);
                    this.menuItemShedule.setDisable(false);
                    this.menuItemInscription.setDisable(false);
                    this.menuItemReport.setDisable(false);

                    this.btnLogin.setVisible(false);
                    this.textUser.setEditable(false);
                    this.textPassword.setEditable(false);
                }
                if (user.getType().equals("Student")) {//Estudiante solo tiene acceso a la funcionalidad de reportes y debera digitar su carnet para solicitar su informacion
                    unblockMenuItems();//Bloquea los demas menu items
                    this.btnLogin.setVisible(false);
                    this.textUser.setEditable(false);
                    this.textPassword.setEditable(false);
                }
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    public void unblockMenuItems() {
        this.menuBar.setDisable(false);
        this.menuItemUser.setDisable(true);
        this.menuItemCareers.setDisable(true);
        this.menuItemStudent.setDisable(true);
        this.menuItemCourse.setDisable(true);
        this.menuItemShedule.setDisable(true);
        this.menuItemInscription.setDisable(true);
        this.menuItemReport.setDisable(false);
    }

    public void blockMenuItems() {
        this.menuBar.setDisable(true);
        this.menuItemUser.setDisable(true);
        this.menuItemCareers.setDisable(true);
        this.menuItemStudent.setDisable(true);
        this.menuItemCourse.setDisable(true);
        this.menuItemShedule.setDisable(true);
        this.menuItemInscription.setDisable(true);
        this.menuItemReport.setDisable(true);
    }

    @FXML
    private void btnExit(ActionEvent event) {//Cierra Sesion
        btnClean(event);
        blockMenuItems();
        this.textUser.setEditable(true);
        this.textPassword.setEditable(true);
        this.btnLogin.setVisible(true);
        this.textPassword.setText("");
        this.textUser.setText("");
        user.setUser("");
        user.setPassword("");
    }

    //************************** fin SEGURIDAD **************************
    @FXML
    private void btnClean(ActionEvent event) {//Limpia la pantalla
        //User
        this.gridUser.setVisible(false);
        this.btnRegisterUser.setVisible(false);
        this.txtFielName.setText("");
        this.txtFielPasword.setText("");

        //Career
        this.gridCareer.setVisible(false);
        this.txtFielDescription.setText("");
        this.btnRegisterCareer.setVisible(false);
    }

    //************************** CAREER **************************
    @FXML
    private void addCareer(ActionEvent event) throws ListException {
        btnClean(event);
        gridCareer.setVisible(true);
        btnRegisterCareer.setVisible(true);
        if (!careerList.isEmpty()) {
            this.txtFielId.setText(String.valueOf(domain.Career.autoId + 1));
        } else {
            this.txtFielId.setText(String.valueOf(domain.Career.autoId + 1));
        }

        this.txtFielId.setEditable(false);
    }

    @FXML
    private void btnRegisterCareer(ActionEvent event) {
        boolean condition = false;
        try {
            if (!this.txtFielId.getText().equals("") && !this.txtFielDescription.getText().equals("")) {
                Career career = new Career(0, txtFielDescription.getText());//El id no es importante porque es consecutivo

                if (careerList.isEmpty()) {//Como es el primer valor lo agrega si o si
                    careerList.add(career);
                    xmlCareer.writeXML(careerAddress, "Career", career.getDataName(), career.getData());
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Career successfully registered");
                    alert.showAndWait();
                    condition = true;
                }
                if (!careerList.contains(career)) {//Si no contiene al user, agregarlo //
                    careerList.add(career);
                    xmlCareer.writeXML(careerAddress, "Career", career.getDataName(), career.getData());
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Career successfully registered");
                    alert.showAndWait();
                    condition = true;
                } else {

                    if (condition == false) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("There is already a career in the system");
                        alert.showAndWait();
                    }

                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("");
                alert.setContentText("Do you want to add another Career?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    addCareer(event);
                } else {
                    btnClean(event);
                }

            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("You need to fill all the empty field.");
                alert.showAndWait();
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private void modifyCareer(ActionEvent event) {
    }

    @FXML
    private void removeCareer(ActionEvent event) {
        btnClean(event);

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Remove Career");
        dialog.setHeaderText("");
        dialog.setContentText("Input the description of the career that you want to delete:");
        Optional<String> description = dialog.showAndWait();//recupera el valor de la ventana 

        Career career = new Career(description.get());
        try {
            if (careerList.contains(career)) {
//                for (int i = 0; i < studentList.size(); i++) {
//                    Student student = (Student)studentList.getNode(i).data;
////                    if (student.get) {
////                    }
//                }
                careerList.remove(career);
                Career careerAux = new Career((Career) careerList.getNode(1).getData());
                xmlCareer.createXML("Careers", careerAddress, "CareerSystem");
                for (int i = 1; i <= careerList.size(); i++) {
                    careerAux = (Career) careerList.getNode(i).getData();
                    xmlCareer.writeXML(careerAddress, "Career", careerAux.getDataName(), careerAux.getData());
                }

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The career: " + description + " was remove from the system");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("There is no career with this description");
                alert.showAndWait();
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("");
            alert.setContentText("Do you want to delete another Career?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                removeCareer(event);
            } else {
                btnClean(event);
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    //************************** FIN CAREER **************************
    //************************** STUDENT **************************
    @FXML
    private void addStudent(ActionEvent event) {

    }

    //************************** FIN STUDENT **************************
}//end class
