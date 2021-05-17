package domain;

/**
 *
 * @author juanp
 */
public class Career {

    //Atributos
    private int id;
    private String description;

    //Consecutivo
    public static int autoId; //Se incrementa segun se crean estos objetos

    //Constructor Default
    public Career() {
    }

    //Constructor Sobrecargado
    public Career(int id,String description) {
        this.id = autoId;
        autoId++;
        this.description = description;
    }

    //Constructor Copia
    public Career(Career career) {
        this.autoId = career.autoId;
        this.id = career.id;
        this.description = career.description;
    }

    //Constructor Remove,Contains
    public Career(String description) {
        this.description = description;
    }

    //Set y Get
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int gatAutoID(){
        return autoId;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //XML
    public String[] getDataName() {
        String[] dataName = {"Id", "Description"};
        return dataName;
    }

    public String[] getData() {
        String[] data = {String.valueOf(id), description};
        return data;
    }

    @Override
    public String toString() {
        return "Career{" + "id=" + id + ", description=" + description + '}';
    }

}//end class
