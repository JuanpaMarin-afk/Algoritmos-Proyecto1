package domain;

/**
 *
 * @author juanp
 */
public class Security {

    //Atributos
    private String type;
    private String user;
    private String password;

    //Constructor Sobrecargado
    public Security(String type, String user, String password) {
        this.type = type;
        this.user = user;
        this.password = password;
    }

    //Constructor Remove, Contains
    public Security(String user) {
        this.user = user;
    }

    //Constructor Copia
    public Security(Security security) {
        this.type = security.type;
        this.user = security.user;
        this.password = security.password;
    }

    public Security() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Set Y Get
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getDataName() {
        String[] dataName = {"Type", "Name", "Password"};
        return dataName;
    }

    public String[] getData() {
        String[] data = {type, user, password};
        return data;
    }

    @Override
    public String toString() {
        return "Security{" + "type=" + type + ", user=" + user + ", password=" + password + '}';
    }

}//end class
