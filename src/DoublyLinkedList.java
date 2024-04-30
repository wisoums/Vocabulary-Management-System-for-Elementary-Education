
/**
 * Represents a doubly linked list that stores elements of type Vocab.
 * Each element in the list is contained in a Node, which has references to both the next and previous nodes,
 * allowing bidirectional traversal.
 */
public class DoublyLinkedList {

    /**
     * Represents a node in the DoublyLinkedList.
     * Each node stores a value of type Vocab and references to both the next and previous nodes.
     */

    public class Node{
        private Vocab value;
        private Node previous;
        private Node next;

        /**
         * Default constructor. Initializes a new instance of Node with null references for value, previous, and next.
         */
        public Node()
        {
            value=null;
            previous =null;
            next =null;
        }

        /**
         * Constructs a new Node with specified value, previous node, and next node.
         * @param item The Vocab object to store in the node.
         * @param prev The previous Node in the list.
         * @param next The next Node in the list.
         */

        public Node(Vocab item,Node prev,Node next)
        {
            this.value=item;
            this.previous =prev;
            this.next =next;
        }

        public Vocab getValue()
        {
            return this.value;
        }

        public Node getNext() {
            return this.next;
        }


    }
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList()
    {
        head=null;
        tail =null;
        size=0;
    }
    public Node getHead()
    {
        return this.head;
    }

    /**
     * Adds a new node with the specified Vocab object at the head of the list.
     * If the list is empty, the new node becomes both head and tail.
     * @param newVocab The Vocab object to add at the head of the list.
     */

    public void addAtHead(Vocab newVocab)
    {
        if(size==0)
        {
            head = new Node(newVocab,null,null);
            tail=head;
        }
        else
        {
            Node oldHead = head;
            this.head = new Node(newVocab,null,head);
            oldHead.previous = head;
        }
        size++;
    }

    /**
     * Adds a new node with the specified Vocab object at the tail of the list.
     * If the list is empty, this method delegates to addAtHead to set the first element.
     * @param newVocab The Vocab object to add at the tail of the list.
     */

    public void addAtTail(Vocab newVocab)
    {
        if (size==0)
        {
            addAtHead(newVocab);
        }
        else
        {
            Node oldTail = tail;
            this.tail = new Node(newVocab,tail,null);

            oldTail.next = this.tail;
            size++;
        }
    }

    /**
     * Inserts a new node with the specified new Vocab object immediately after the node containing the specified Vocab object.
     * If the target Vocab is not found, the new Vocab is added at the tail of the list.
     * @param vocab The Vocab object after which the new node is to be inserted.
     * @param newVocab The Vocab object to insert.
     */

    public void addAfter(Vocab vocab,Vocab newVocab)
    {
        boolean found = false;
        if(size==0)
        {
            return;
        }
        else
        {
            Node position = head;
            while (position.next!=null)
            {
                if(position.value.equals(vocab))
                {
                    Node oldNode = position.next;
                    position.next=new Node(newVocab,position,position.next);
                    oldNode.previous = position.next;
                    size++;
                    found = true;
                    break;
                }
                position = position.next;
            }

            if (found==false)
            {
                addAtTail(newVocab);
            }
        }
    }

    /**
     * Inserts a new node with the specified new Vocab object immediately before the node containing the specified Vocab object.
     * If the target Vocab is at the head, the new Vocab is added at the head of the list.
     * @param vocab The Vocab object before which the new node is to be inserted.
     * @param newVocab The Vocab object to insert.
     */
    public void addBefore(Vocab vocab,Vocab newVocab)
    {
        boolean found = false;
        if (size==0)
        {
            return;
        }
        else if (head.value.equals(vocab))
        {
            addAtHead(newVocab);
        }
        else
        {
            Node position = head;
            while (position.next!=null)
            {
                if (position.next.value.equals(vocab))
                {
                    Node oldPrev = position;
                    Node oldNode = position.next;
                    position.next = new Node(newVocab,position,position.next);
                    oldNode.previous = position.next;
                    oldPrev.next = position.next;
                    size++;
                    found = true;
                    break;

                }
                position = position.next;
            }
        }

    }

    /**
     * Removes and returns the Vocab object at the head of the list.
     * If the list is empty, returns null.
     * @return The Vocab object formerly at the head of the list, or null if the list was empty.
     */
    public Vocab removeHead()
    {
        if (size==0)
        {
            return null;
        }
        else if (size==1)
        {
            Vocab value = head.value;
            head=null;
            tail=null;
            size--;
            return value;
        }
        else
        {
            Vocab value = head.value;
            head = head.next;
            head.previous=null;
            size--;
            return value;
        }
    }

    /**
     * Removes and returns the Vocab object at the tail of the list.
     * If the list is empty, returns null.
     * @return The Vocab object formerly at the tail of the list, or null if the list was empty.
     */
    public Vocab removeTail()
    {
        if (size==0)
        {
            return null;
        }
        else if (size==1)
        {
            return removeHead();
        }
        else
        {
            Vocab value = tail.value;
            tail = tail.previous;
            tail.next =null;
            size--;
            return value;
        }
    }


    /**
     * Removes the node containing the specified Vocab object from the list and returns its value.
     * If the Vocab is not found, or the list is empty, returns null.
     * @param vocab The Vocab object to remove.
     * @return The Vocab object that was removed, or null if it was not found or the list was empty.
     */
    public Vocab removeValue(Vocab vocab)
    {
        Vocab value=null;
        if (size==0)
        {
            value = null;
        }
        else {

            Node position = head;
            while (position!=null)
            {
                if (position.value.equals(vocab))
                {
                    if (position.previous==null)
                    {
                        value = head.value;
                        removeHead();
                    }
                    else if (position.next==null)
                    {
                        value=tail.value;
                        removeTail();
                    }
                    else {
                        value = position.value;
                        position.previous.next = position.next;
                        position.next.previous = position.previous;
                        size--;
                        break;
                    }
                }
                position = position.next;
            }
        }
        return value;
    }

    public int getSize()
    {
        return this.size;
    }

    /**
     * This method is supposed to remove the node after the node containing the specified Vocab object, but it appears incomplete.
     * @param vocab The Vocab object after which the node should be removed.
     * @return The value of the node that was supposed to be removed, or null if applicable conditions are not met.
     */
    public Vocab removeAfter(Vocab vocab)
    {
        Vocab value = null;
        if (size<2)
        {
            return value;
        }
        else
        {
            return value;
        }
    }

    /**
     * Displays all the Vocab objects in the list from head to tail.
     */
    public void displayForward() {
        if (size == 0) {
            System.out.println("The list is empty");
        } else {
            System.out.println("The list_vocab has " + size + " element(s):\n");
            Node position = head;
            while (position != null) {
                System.out.println(position.value);
                position = position.next;
            }
        }
    }

    /**
     * Checks if there is a next node in the list from the current node.
     * @param node The current node from which to check for a next node.
     * @return true if there is a next node, false otherwise.
     */
    public boolean hasNext(Node node) {
        return node != null ;
    }

}
