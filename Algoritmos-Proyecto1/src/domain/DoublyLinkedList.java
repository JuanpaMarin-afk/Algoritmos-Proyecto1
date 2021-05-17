package domain;

/**
 *
 * @author juanp
 */
public class DoublyLinkedList implements List {

    private Node first;//apunta al inicio de la lista dinamica

    public DoublyLinkedList() {//Constructor
        this.first = null;//la lista todavia no existe
    }

    @Override
    public int size() throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty : SinglyLinkedList");
        }
        Node aux = first;
        int count = 0;
        while (aux != null) {
            count++;
            aux = aux.next;
        }

        return count;
    }

    @Override
    public void clear() {//Anula la lista, quita todos los valores
        first = null;
    }

    @Override
    public boolean isEmpty() {//true si la lista esta vacia
        return first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty : SinglyLinkedList");
        }
        Node aux = first;//el aux es apra moverme por la lita hasta el ultimo elemento
        while (aux != null) {
            if (util.Utility.equals(aux.data, element)) {
                return true;
            }
            aux = aux.next;
        }

        return false;
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = newNode;
        } else {
            Node aux = first;//el aux es apra moverme por la lita hasta el ultimo elemento
            while (aux.next != null) {
                aux = aux.next;
            }
            //cuando se sale del while quiere decir que aux.next == null
            aux.next = newNode;
            //hago el doble enlace
            newNode.prev = aux;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = newNode;
        }
        newNode.next = first;
        //hago doble enlace
        first.prev = newNode;
        first = newNode;

    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {
        Node newNode = new Node(element);
        //CASO 1. LA LISTA ESTA VACIA
        if (isEmpty()) {
            first = newNode;
        } else {
            //CAS0 2. first.next es null y el elemento a insertar es menor al del inicio
            if (util.Utility.greaterT(first.data, element)) {
                //hago doble enlace
                first.prev = newNode;
                newNode.next = first;
                first = newNode;
            } else {
                //CASO 3. TODO LO DEMAS
                Node prev = first;
                Node aux = first.next;
                boolean added = false;
                while (aux != null && !added) {
                    if (util.Utility.lessT(element, aux.data)) {
                        prev.next = newNode;
                        //hago el doble enlace
                        newNode.prev = prev;

                        newNode.next = aux;
                        //hago doble enlace
                        aux.prev = newNode;
                        added = true;
                    }
                    prev = aux;
                    aux = aux.next;
                }
                //si llega aqui, el elemento se agrega al final de la lista
                if (!added) {
                    prev.next = newNode;
                    //hago doble enlace
                    newNode.prev = prev;
                }
            }

        }

    }

    @Override
    public void remove(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty : SinglyLinkedList");
        }
        //CASO 1. EL ELEMENTO A SUPRIMIR ES EL PRIMERO DE LA LISTA
        if (util.Utility.equals(first.data, element)) {
            first = first.next;
        } else {
            //CASO 2. EL ELEMENTO A SUPRIMIR ESTA EN CUALQUIER OTRA POSICION
            Node prev = first; //esto es para dejar rastro, apunta al anterior de aux
            Node aux = first.next;

            while (aux.next != null && !util.Utility.equals(aux.data, element)) {
                prev = aux; //un nodo atras de aux
                aux = aux.next;
            }
            //sale del while cuando alcanza null o cuando encuentra el elemento a suprimir
            if (aux.next != null && util.Utility.equals(aux.data, element)) {
                //desenlazo el nodo
                prev.next = aux.next;
            }
        }
    }

    @Override
    public Object removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty : SinglyLinkedList");
        }
        Object element = first.data;
        first = first.next;//muevo el apuntaador al siguiente nodo
        return element;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty : SinglyLinkedList");
        }
        Node aux = first;
        Node prev = first; //esto es para dejar rastro, apunta al anterior de aux
        while (aux.next != null) {
            prev = aux; //un nodo atras de aux
            aux = aux.next;
        }
        //se sale del while cuando esta en el ultimo nodo
        Object element = aux.data;
        prev.next = null;//desconecto el ultimo nodo
        return element;
    }

    @Override
    public void sort() throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty : SinglyLinkedList");
        }
        for (int i = 1; i <= this.size(); i++) {
            Node actual = this.first;//node actual
            Node siguiente = this.first.next;//node siguiente
            Node anterior = actual;//node anterior
            while (siguiente != null) {
                if (util.Utility.greaterT(actual.getData(), siguiente.getData())) {//mayor a menor
                    if (actual == this.first) {
                        actual.next = siguiente.next;
                        actual.prev = siguiente;
                        siguiente.next = actual;
                        siguiente.prev = null;
                        this.first = siguiente;
                        actual = siguiente;
                        siguiente = actual.next;
                        anterior = this.first;
                    } else {
                        anterior.next = siguiente;
                        actual.next = siguiente.next;
                        actual.prev = siguiente;
                        siguiente.next = actual;
                        siguiente.prev = anterior;
                        anterior = siguiente;
                        siguiente = actual.next;
                    }
                } else {
                    anterior = actual;
                    actual = siguiente;
                    siguiente = siguiente.next;
                }
            }
        }
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty : SinglyLinkedList");
        }
        Node aux = first;
        int index = 1; // el primer nodo estara en el indice 1
        while (aux != null) {
            if (util.Utility.equals(aux.data, element)) {
                return index; // ya lo encontro
            }
            index++;
            aux = aux.next;
        }
        return -1; // significa que el elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty : SinglyLinkedList");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty : SinglyLinkedList");
        }
        Node aux = first;
        while (aux.next != null) {
            aux = aux.next;
        }
        return aux.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {

        return element;
    }

    @Override
    public Object getNext(Object element) throws ListException {

        return element;
    }

    @Override
    public Node getNode(int index) throws ListException {
        if (isEmpty()) {
            throw new ListException("List is empty");
        }
        Node aux = this.first;
        int cont = 1;
        while (cont < index) {
            cont++;
            aux = aux.next;
        }
        if (cont == index) {
            return aux;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String result = "Doubly Linked List\n";
        Node aux = first;//el aux es apra moverme por la lita hasta el ultimo elemento
        while (aux != null) {
            result += aux.data + " \n ";
            aux = aux.next;
        }
        return result;
    }

}//end class
