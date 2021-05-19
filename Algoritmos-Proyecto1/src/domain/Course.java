package domain;

/**
 *
 * @author nicole
 */
public class Course {
    
    //Atributos
    private int idCourse;
    private String nameCourse;

    //Constructor Sobrecargado
    public Course(int idCourse, String nameCourse) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
    }
    
    //Constructor Copia
    public Course(Course course){
        this.idCourse = course.idCourse;
        this.nameCourse = course.nameCourse;
    }
    
    //Constructor Remove, Contains
    public Course(int idCourse) {
        this.idCourse = idCourse;
    }
    
    //Set y Get
    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
    
    //XML
    public String [] getDataNameCourse(){
        String[] dataNameCourse = {"IdCourse", "NameCourse"};
        return dataNameCourse;
    }
    public String[] getData (){
        String [ ] data = {String.valueOf(idCourse), nameCourse};
        return data;
}
    //ToString
    @Override
    public String toString() {
        return "Course{" + "idCourse=" + idCourse + ", nameCourse=" + nameCourse + '}';
    }
    
}
