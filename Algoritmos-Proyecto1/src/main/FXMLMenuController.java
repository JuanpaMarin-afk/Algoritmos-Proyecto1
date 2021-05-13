package main;

import domain.CircularDoublyLinkedList;
import domain.DoublyLinkedList;
import domain.SinglyLinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {//Tipo constructor
       
    }    
    
    
    
}//end class
