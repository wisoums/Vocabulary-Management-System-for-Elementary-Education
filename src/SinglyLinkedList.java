import java.util.ArrayList;

public class SinglyLinkedList {


    public class Node {
        private String  value;
        private Node next;

        /**
         * Default constructor. Initializes a new instance of Node with null references for value and next.
         */
        public Node() {
            value = null;
            next = null;
        }

        /**
         * Constructs a new Node with specified value and next node.
         * @param value The String value to store in the node.
         * @param next The next Node in the list.
         */
        public Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return this.value;
        }

        public Node getNext() {
            return this.next;
        }
    }

    private Node head;
    private  int size;

    /**
     * Constructs an empty SinglyLinkedList.
     */
    public SinglyLinkedList()
    {
        this.head = null;
        this.size=0;
    }
    public Node getHead()
    {
        return this.head;
    }

    /**
     * Checks if the list is empty.
     * @return true if the list has no nodes, otherwise false.
     */
    public boolean isEmpty()
    {
        return size==0;
    }

    /**
     * Adds a new node with the specified String value at the head of the list.
     * @param newValue The String value to add at the head of the list.
     */
    public void addAtHead(String newValue)
    {
        this.head = new Node(newValue,head);
        size++;
    }

    /**
     * Adds a new node with the specified String value at the end of the list.
     * If the list is empty, this method delegates to addAtHead.
     * @param newValue The String value to add at the end of the list.
     */
    public void addAtEnd(String newValue)
    {
        if(head==null)
        {
            addAtHead(newValue);
        }
        else {

            Node position = head;
            while (position.next != null) {
                position = position.next;
            }
            position.next = new Node(newValue,null);
            size++;
        }
    }

    /**
     * Modifies the value of the first node that matches the specified value (valueBefore) to a new value (valueAfter).
     * @param valueBefore The current value to find in the list.
     * @param valueAfter The new value to replace the old value with.
     */
    public void modifyValue(String valueBefore,String valueAfter)
    {
        String value=null;
        if (head.value.equals(valueBefore))
        {
            head.value = valueAfter;
        }
        else {
            Node position = head;
            while (position.next != null) {
                if (position.next.value.equals(valueBefore)) {

                    position.next.value = valueAfter;
                    break;
                }
                position = position.next;
            }
        }
    }

    /**
     * Returns a list of all values in the linked list that start with a specified letter.
     * @param letter The character that the values should start with.
     * @return An ArrayList of String containing all values that start with the specified letter.
     */
    public ArrayList<String> startsWith(char letter)
    {
        ArrayList<String> list = new ArrayList<String>();
        String value=null;
        if (head.value.charAt(0)==letter)
        {
            list.add(head.value);
        }
            Node  position = head;
            while (position.next != null) {
                if (position.next.value.charAt(0)==letter) {

                    list.add(position.next.value);
                }
                position = position.next;
            }
            return list;
    }

    /**
     * Inserts a new node with the specified new String value immediately after the node containing a specified String value.
     * If the specified value is not found, the new value is added at the end of the list.
     * @param str The String value after which the new node is to be inserted.
     * @param newStr The String value to insert.
     */
    public void addAfter(String str,String newStr)
    {
        boolean found = false;
            Node position = head;

            while (position.next!=null) {

                if (position.value.equals(str)) {
                    position.next = new Node(newStr, position.next);
                    size++;
                    found=true;
                    break;
                }
                position = position.next;
            }
            if (found==false&&position.value.equals(str))
            {
                addAtEnd(newStr);
            }
    }

    /**
     * Inserts a new node with the specified new String value immediately before the node containing a specified String value.
     * If the specified value is at the head, the new value is added at the head of the list.
     * @param str The String value before which the new node is to be inserted.
     * @param newStr The String value to insert.
     */
    public void addBefore(String str,String newStr)
    {
       Node position = head;
       Node before = null;

       while (position.next!=null)
       {
           if (position.value.equals(str))
           {
               Node newNode = new Node(newStr,position);
               if (before!=null)
               {
                  before.next = newNode;
               }
               else
               {
                   addAtHead(newStr);
               }
               size++;
               break;
           }
           before =position;
           position = position.next;
       }
    }

    /**
     * Removes and returns the value at the head of the list.
     * If the list is empty, returns null.
     * @return The String value formerly at the head of the list, or null if the list was empty.
     */
    public String removeHead()
    {
        if (head!=null)
        {
            Node temp = head;

            head = head.next;

            size--;
            return  temp.value;
        }
        else
        {
            return null;
        }
    }

    /**
     * Removes and returns the value at the end of the list.
     * If the list is empty, returns null.
     * @return The String value formerly at the end of the list, or null if the list was empty.
     */
    public String removeEnd()
    {
        if (head==null)
        {
            return null;
        }
        else if (size==1)
        {
            String  value = head.value;
            head =null;
            size--;
            return value;
        }
        else
        {
            Node position = head;
            while (position.next.next!=null)
            {
                position = position.next;
            }
            String  value = position.next.value;
            position.next=null;
            size--;
            return value;
        }
    }

    /**
     * Removes the first node containing the specified String value and returns its value.
     * If the value is not found, returns null.
     * @param str The String value to remove from the list.
     * @return The String value removed, or null if not found.
     */
    public String removeValue(String str)
    {
        String  value=null;
        if (head.value.equals(str))
        {
            value = head.value;
            removeHead();
        }
        else {
            Node  position = head;
            while (position.next != null) {
                if (position.next.value.equals(str)) {

                    value = position.next.value;
                    position.next = position.next.next;
                    size--;
                    break;
                }
                position = position.next;
            }
        }
        return value;
    }

    /**
     * Checks if the list contains a node with the specified String value.
     * @param str The String value to search for in the list.
     * @return true if the value is found, otherwise false.
     */
    public boolean contains(String str)
    {
        boolean found =false;
        String  value=null;
        if (head.value.equals(str))
        {
            found =true;
        }
        else {
            Node  position = head;
            while (position.next != null) {
                if (position.next.value.equals(str)) {
                    found = true;
                    break;
                }
                position = position.next;
            }
        }
        return found;
    }

    /**
     * Removes the node immediately following the node containing the specified String value and returns the removed value.
     * If the specified value is not found or there is no node after the specified node, returns null.
     * @param str The String value after which the node will be removed.
     * @return The String value of the removed node, or null if applicable.
     */
    public String removeAfter(String str)
    {
        boolean found = false;
        String  value=null;
        if(size<2)
        {
            value =null;
        }
        //good case
        else {
            Node  position = head;
            while (position.next != null) {
                if (position.value.equals(str)) {

                    value = position.next.value;
                    position.next = position.next.next;
                    size--;
                    found= true;
                    break;
                }
                position = position.next;
            }
            if (position.next==null&&found==false)
            {
                value=null;
            }
        }
        return value;
    }

    /**
     * Returns the number of nodes in the list.
     * @return The size of the list.
     */
    public int getSize()
    {
        return this.size;
    }

    /**
     * Builds and returns a formatted string representing all the items in the list.
     * Each item is numbered and displayed in rows, with 4 items per row if possible.
     * @return A formatted string of all list items.
     */
    public String displayList()
    {    StringBuilder str = new StringBuilder();
        int count=1;
        if (head==null)
        {
            str.append("There are no items");
        }
        else {
            //System.out.println("Your list has " + size + " element(s): ");
            Node position = head;
            while (position != null) {
                str.append(String.format("%-30s",count+": "+ position.value));
               //str += count+": "+position.value.toString()+"\t\t\t";
               if (count%4==0)
               {
                   //str+="\n";
                   str.append("\n");
               }
                position = position.next;
                count++;
            }
        }
        return str.toString();
    }


    /**
     * Empties the list by setting the head to null.
     */
    public void emptyList()
    {
        head = null;
    }

    /**
     * Creates and returns a deep copy of this list.
     * Each node in this list is copied and added to the new list.
     * @return A new SinglyLinkedList instance that is a copy of this list.
     */
    public SinglyLinkedList clone() {
        SinglyLinkedList clonedList = new SinglyLinkedList();

        // Traverse the original list
        Node current = this.head;
        while (current != null) {
            // Add each element to the cloned list
            clonedList.addAtEnd(current.value);
            current = current.next;
        }
        return clonedList;
    }



    //contains

    //modify












}
