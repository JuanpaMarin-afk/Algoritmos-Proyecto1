package domain;

/**
 *
 * @author juanp
 */
public class Node {

    public Object data; //El dato almancenado en el nodo
    public Node next; //El apuntador al sgte nodo
    public Node prev;

    public Node(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Node() {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}//end class
