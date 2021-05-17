package domain;

import java.util.Date;

/**
 *
 * @author juanp
 */
public class Student {

    //Atributos
    private int id; //Cedula
    private String studentID;//Carnet
    private String lastName;
    private String firstName;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private String address;
    private int careerID; //Codigo de la carrera

    //Constructor Sobrecargado
    public Student(int id, String studentID, String lastName, String firstName, Date birthday, String phoneNumber, String email, String address, int careerID) {
        this.id = id;
        this.studentID = studentID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.careerID = careerID;
    }

    //Constructor Default
    public Student() {
    }

    //Constructor Remove,Contains
    public Student(String studentID) {
        this.studentID = studentID;
    }

    //Constructor Copia
    public Student(Student student) {
        this.id = student.id;
        this.studentID = student.studentID;
        this.lastName = student.lastName;
        this.firstName = student.firstName;
        this.birthday = student.birthday;
        this.phoneNumber = student.phoneNumber;
        this.email = student.email;
        this.address = student.address;
        this.careerID = student.careerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCareerID() {
        return careerID;
    }

    public void setCareerID(int careerID) {
        this.careerID = careerID;
    }

    //XML
    public String[] getDataName() {
        String[] dataName = {"Id", "StudentID", "LastName", "Birthday" , "PhoneNumber", "Email", "Address", "CareerID"};
        return dataName;
    }

    public String[] getData() {
        String[] data = {String.valueOf(id), studentID, lastName, firstName, util.Utility.dateFormat(birthday), phoneNumber, email, address, String.valueOf(careerID)};
        return data;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", studentID=" + studentID + ", lastName=" + lastName + ", firstName=" + firstName + ", birthday=" + birthday + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", careerID=" + careerID + '}';
    }
}// end class
