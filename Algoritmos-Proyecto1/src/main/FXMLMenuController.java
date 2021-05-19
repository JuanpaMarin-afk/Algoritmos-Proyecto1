package main;

import data.FileXML;
import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Security;
import domain.SinglyLinkedList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
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

    //**************************  Seucurity   **************************
    //TextFields para poder iniciar sesion
    @FXML
    private TextField textUserSecurity;
    @FXML
    private PasswordField textPasswordSecurity;

    //Botones 
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnClean;

    //**************************  MENU BAR   **************************
    @FXML
    private MenuBar menuBar;

    //Security (User)
    @FXML
    private Menu menuItemUser;
    @FXML
    private MenuItem menuUserAdd;
    @FXML
    private MenuItem menuUserDisplay;
    //fin Security(User)

    //Career
    @FXML
    private Menu menuItemCareers;
    @FXML
    private MenuItem addCareer;
    @FXML
    private MenuItem modifyCareer;
    @FXML
    private MenuItem removeCareer;
    @FXML
    private MenuItem displayCareer;
    //fin Career

    //Student
    @FXML
    private Menu menuItemStudent;
    @FXML
    private MenuItem addStudent;
    //fin Student
    
    @FXML
    private Menu menuItemCourse;
    @FXML
    private Menu menuItemShedule;
    @FXML
    private Menu menuItemInscription;
    @FXML
    private Menu menuItemReport;

    //**************************  FIN MENU BAR   **************************
    
    //**************************  User   **************************
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

    //Tabla de User
    @FXML
    private TableView<Security> tableUser;     
    @FXML
    private TableColumn<Security, String> tCUserType;
    @FXML
    private TableColumn<Security, String> tCUserUser;
    @FXML
    private TableColumn<Security, String> tCUserPasword;

    //**************************   fin User  **************************
    
    //**************************    Career   **************************
    //Grid de carrera
    @FXML
    private GridPane gridCareer;
    //TextFiel de registrar
    @FXML
    private TextField txtFielId;
    @FXML
    private TextField txtFielDescription;
    //Boton de registrar Carrera
    @FXML
    private Button btnRegisterCareer;  
    
    //tabla Career
    @FXML
    private TableView<?> tableCarrer;
    @FXML
    private TableColumn<?, ?> tCCourseId;
    @FXML
    private TableColumn<?, ?> tCCourseName;
    @FXML
    private DatePicker DPickerStudent;

    //**************************  fin Career  **************************
    
    //**************************    Student   **************************
    //Grid de Estudiante
    @FXML
    private GridPane gridStudent;
    //Boton de registrar Estudiante
    @FXML
    private Button btnRegisterStudent;
    //TextFiel de gridregistrar estudiante
        @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtStudentLastN;
    @FXML
    private TextField txtStudentFirstN;
    @FXML
    private TextField txtStudentPhone;
    @FXML
    private TextField txtStudentEmail;
    @FXML
    private TextField txtStudentAddress;
    
    //Tabla de Estudiante
    @FXML
    private TableView<?> tableStudent;
    
    @FXML
    private TableColumn<?, ?> tcStudentid;
    @FXML
    private TableColumn<?, ?> tcStudentCollegeId;
    @FXML
    private TableColumn<?, ?> tcStudentLastName;
    @FXML
    private TableColumn<?, ?> tcStudentFirstName;
    @FXML
    private TableColumn<?, ?> tcStudentBirthday;
    @FXML
    private TableColumn<?, ?> tcStudentPhone;
    @FXML
    private TableColumn<?, ?> tcStudentEmail;
    @FXML
    private TableColumn<?, ?> tcStudentAddress;
    
    //**************************  fin Student  **************************
    
    //**************************    Course     **************************
    @FXML
    private GridPane gridCourse;
    //TextFiel de gridregistrar Curso
    @FXML
    private TextField txtCourseId;
    @FXML
    private TextField txtCourseName;
    //Boton de registrar Curso
    @FXML
    private Button btnRegisterCourse;
    
    //Tabla de Curso
    
    
    
    
    
    
    //**************************  fin Course   **************************
    
    
    
    
    // XML Users
    FileXML xmlUser;
    String userAddress = "UserSystem.xml";

    //XML Careers   
    FileXML xmlCareer;
    String careerAddress = "CareerSystem.xml";
//*******************************************************************************





    //XML Students
    //XML Courses
    //XML Shedule
    @Override
    public void initialize(URL url, ResourceBundle rb) {//Tipo constructor
        //Boton clean, basurerito
        this.btnClean.setVisible(false);

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

    //************************** SEGURIDAD **************************
    @FXML
    private void btnLogin(ActionEvent event) {//Inicia Sesion
        try {
            user.setUser(this.textUserSecurity.getText());
            user.setPassword(this.textPasswordSecurity.getText());
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
                    this.textUserSecurity.setEditable(false);
                    this.textPasswordSecurity.setEditable(false);
                    this.btnClean.setVisible(true);
                }
                if (user.getType().equals("Student")) {//Estudiante solo tiene acceso a la funcionalidad de reportes y debera digitar su carnet para solicitar su informacion
                    unblockMenuItems();//Bloquea los demas menu items
                    this.btnLogin.setVisible(false);
                    this.textUserSecurity.setEditable(false);
                    this.textPasswordSecurity.setEditable(false);
                    this.btnClean.setVisible(true);
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
        this.textUserSecurity.setEditable(true);
        this.textPasswordSecurity.setEditable(true);
        this.btnLogin.setVisible(true);
        this.textPasswordSecurity.setText("");
        this.textUserSecurity.setText("");
        //Metodo que actualiza la base de datos al cerrar sesion
        //updateXML();
        user.setUser("");
        user.setPassword("");
        this.btnClean.setVisible(false);

    }

    public void updateXML() {
        if (user.getType().equals("Administrator")) {
            try {
                //Guarda User
                xmlUser.createXML("Users", userAddress, "UserSystem");
                Security security = new Security((Security) loginList.getNode(1).getData());
                for (int i = 1; i <= loginList.size(); i++) {
                    security = (Security) loginList.getNode(i).getData();
                    xmlUser.writeXML(userAddress, "User", security.getDataName(), security.getData());
                }

                //Guarda Career
                xmlCareer.createXML("Careers", careerAddress, "CareerSystem");
                Career careerAux = new Career((Career) careerList.getNode(1).getData());
                for (int i = 1; i <= careerList.size(); i++) {
                    careerAux = (Career) careerList.getNode(i).getData();
                    xmlCareer.writeXML(careerAddress, "Career", careerAux.getDataName(), careerAux.getData());
                }

            } catch (Exception e) {

            }
        }
    }

    //************************** fin SEGURIDAD **************************
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
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("User successfully registered");
                    alert.showAndWait();
                    condition = true;
                }
                if (!loginList.contains(security)) {//Si no contiene al user, agregarlo //
                    loginList.add(security);
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

    //************************** fin USER **************************
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
            this.txtFielId.setText(String.valueOf(domain.Career.autoId));
        } else {
            this.txtFielId.setText(String.valueOf(domain.Career.autoId));
        }

        this.txtFielId.setEditable(false);
    }

    @FXML
    private void btnRegisterCareer(ActionEvent event) {
        boolean condition = false;
        try {
            if (!this.txtFielId.getText().equals("") && !this.txtFielDescription.getText().equals("")) {
                Career career = new Career(txtFielDescription.getText());//El id no es importante porque es consecutivo

                if (careerList.isEmpty()) {//Como es el primer valor lo agrega si o si
                    Career career2 = new Career(0, txtFielDescription.getText());
                    careerList.add(career2);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Career successfully registered");
                    alert.showAndWait();
                    condition = true;
                }
                if (!careerList.contains(career)) {//Si no contiene al user, agregarlo //
                    Career career2 = new Career(0, txtFielDescription.getText());
                    careerList.add(career2);
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
            if (!careerList.isEmpty()) {
                if (careerList.contains(career)) {
                    careerList.remove(career);

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

    @FXML
    private void displayCareer(ActionEvent event) {
    }

    //************************** FIN CAREER **************************
    //************************** STUDENT **************************
    @FXML
    private void addStudent(ActionEvent event) {

    }

    //************************** FIN STUDENT **************************

    @FXML
    private void btnRegisterStudent(ActionEvent event) {
    }

    @FXML
    private void btnRegisterCourse(ActionEvent event) {
    }
}//end class
