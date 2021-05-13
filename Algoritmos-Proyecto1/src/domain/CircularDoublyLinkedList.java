package domain;

/**
 *
 * @author juanp
 */
public class CircularDoublyLinkedList implements List {

    private Node first;//apunta al inicio de la lista dinamica    
    private Node last; // apunta al nodo final de la lista

    public CircularDoublyLinkedList() {//Constructor
        this.first = null;
        this.last = null;//la lista todavia no existe
    }

    @Override
    public int size() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList: Vacia");
        }
        Node aux = first;
        int count = 0;
        while (aux != last) {
            count++;
            aux = aux.next;
        }
        //Se sale cuando esta en el ultimo nodo
        return count + 1;// +1 para que cuente el ultimo nodo
    }

    @Override
    public void clear() {//Anula la lista, quita todos los valores
        this.first = this.last = null;
    }

    @Override
    public boolean isEmpty() {//true si la lista esta vacia
        return first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList: Vacia");
        }
        Node aux = first;//El aux es para moverme por la lita hasta el ultimo elemento
        while (aux != last) {
            if (util.Utility.equals(aux.data, element)) {
                return true;
            }
            aux = aux.next;
        }
        //Se sale cuando aux == last, en este caso solo resta verificar si el elemento a buscar esta en ese nodo
        return util.Utility.equals(aux.data, element);
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = this.last = newNode;
        } else {
            last.next = newNode;

            //Hago el doble enlace
            newNode.prev = last;

            last = newNode;// Muevo last a que apunte al ultimo nodo

            //Hago el enlace circular
            last.next = first;

            //Hago el doble enlace
            first.prev = last;

        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = this.last = newNode;
        }
        newNode.next = first;
        //Hago doble enlace
        first.prev = newNode;

        first = newNode;

        //Hago enlace circular y doble
        last.next = first;
        first.prev = last;
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {//CORREGIR
        Node newNode = new Node(element);
        //CASO 1. LA LISTA ESTA VACIA
        if (isEmpty()) {
            this.first = this.last = newNode;
        } else {
            //CAS0 2. first.next es null y el elemento a insertar es menor al del inicio
            if (util.Utility.greaterT(first.data, element)) {
                newNode.next = first;
                //Hago el doble enlace
                first.prev = newNode;

                first = newNode;
            } else {
                //CASO 3. TODO LO DEMAS
                Node prev = first;
                Node aux = first.next;
                boolean added = false;
                while (aux != null && !added) {
                    if (util.Utility.lessT(element, aux.data)) {
                        prev.next = newNode;
                        //Doble enlace
                        newNode.prev = prev;

                        newNode.next = aux;
                        aux.prev = newNode;
                        added = true;
                    }
                    prev = aux;
                    aux = aux.next;
                }

                //Si llega aqui se enlaza cuando aux==last
                if (util.Utility.lessT(element, aux.data) && !added) {
                    prev.next = newNode;
                    //Doble enlace
                    newNode.prev = prev;

                    newNode.next = aux;
                    //Hago doble enlace
                    aux.next = newNode;
                } else {
                    //En este caso se enlaza al final
                    if (!added) {
                        aux.next = newNode;
                        //Hago el doble enlace
                        newNode.prev = aux;
                        //Muevo last al ultimo nodo
                        last = newNode;
                    }
                }

            }

        }
        //Hago el enlace circular
        last.next = first;
        first.prev = last;
    }

    @Override
    public void remove(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList: Vacia");
        }
        //CASO 1. EL ELEMENTO A SUPRIMIR ES EL PRIMERO DE LA LISTA
        if (util.Utility.equals(first.data, element)) {
            first = first.next;
        } else {
            //CASO 2. EL ELEMENTO A SUPRIMIR ESTA EN CUALQUIER OTRA POSICION
            Node prev = first; //esto es para dejar rastro, apunta al anterior de aux
            Node aux = first.next;

            while (aux != last && !util.Utility.equals(aux.data, element)) {
                prev = aux; //un nodo atras de aux
                aux = aux.next;
            }
            //Se sale cuano aux == last o cuando encuentra el elemento a suprimir
            if (util.Utility.equals(aux.data, element)) {
                //Desenlazo el nodo
                prev.next = aux.next;
                //Mantengo el doble enlace
                aux.next.prev = prev;
            }
            //Debo asegurarme que las apunte al ultimo nodo
            if (aux == last && util.Utility.equals(aux.data, element)) {//Estamos en el ultimo nodo
                last = prev;
            }
        }
        //Mantengo enlace ciruclar y doble 
        this.last.next = first;
        first.prev = last;

        //Que pasa si solo queda un nodo y es el que quiero eliminar?
        if (first == last && util.Utility.equals(first.data, element)) {
            clear();//En este caso anulamos la lista
        }

    }

    @Override
    public Object removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList: Vacia");
        }
        Object element = first.data;
        first = first.next;//Muevo el apuntaador al siguiente nodo
        //Mantengo el enlace circular y doble
        first.prev = last;
        last.next = first;
        return element;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList: Vacia");
        }
        Node aux = first;
        Node prev = first; //Esto es para dejar rastro, apunta al anterior de aux
        while (aux.next != last) {
            prev = aux; //Un nodo atras de aux
            aux = aux.next;
        }
        //Se sale del while cuando esta en el ultimo nodo
        Object element = aux.data;
        //prev.next = first;//Desconecto el ultimo nodo
        last = prev;

        //Mantengo el enlace circular
        last.next = first;
        first.prev = last;

        return element;
    }

    @Override
    public void sort() throws ListException {//HACER 

    }

    @Override
    public int indexOf(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList: Vacia");
        }
        Node aux = first;
        int index = 1; // El primer nodo estara en el indice 1
        while (aux != last) {
            if (util.Utility.equals(aux.data, element)) {
                return index; //Ya lo encontro
            }
            index++;
            aux = aux.next;
        }
        //Sale cuando el aux == last
        if (util.Utility.equals(aux.data, element)) {
            return index;
        }
        return -1; //Significa que el elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList: Vacia");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList: Vacia");
        }
        Node aux = first;
        while (aux.next != null) {
            aux = aux.next;
        }
        return last.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {//HACER NOSOTROS

        return element;
    }

    @Override
    public Object getNext(Object element) throws ListException {//HACER NOSOTROS

        return element;
    }

    @Override
    public Node getNode(int index) throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList: Vacia");
        }
        Node aux = this.first;
        int cont = 1;
        while (aux != last) {
            if (util.Utility.equals(cont, index)) {
                return aux;
            }
            cont++;
            aux = aux.next;
        }

        if (util.Utility.equals(cont, index)) {
            return aux;
        }
        return null;

    }

    @Override
    public String toString() {
        String result = "Circular Doubly LinkedList\n";
        Node aux = first;//el aux es apra moverme por la lita hasta el ultimo elemento
        while (aux != last) {
            result += aux.data + "\n";
            aux = aux.next;
        }
        return result += aux.data;
    }

}//end class
