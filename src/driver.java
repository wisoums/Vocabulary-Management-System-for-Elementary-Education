import javax.swing.plaf.IconUIResource;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// ---------------------------------------------------------------------
// Assignment 3
// Written by: Wissem Oumsalem (40291712) & Soukayna Haitami (40280964)
// ---------------------------------------------------------------------

/**
 * Wissem Oumsalem (40291712) <br>
 * Soukayna Haitami (40280964) <br>
 * COMP 249 <br>
 * Assignment #3 <br>
 * Due : April 15th, 2024
 *
 */

public class driver{

    /**
     * Method to validate if the user's input is an integer to prevent the program from crashing
     * @param input is the input of the user that is to validate
     * @return the valid integer input
     */

    public static int validIntegerInput(Scanner input){
        int integerInput=0;
        boolean valid = false;
        do{
            if(input.hasNextInt()){
                integerInput = input.nextInt();
                valid=true;
            }else {
                System.out.print("Invalid value. Please enter an integer:");
                input.next();
            }
        }while (!valid);
        return integerInput;
    }

    /**
     * Creates a file with topics and words related to those topics.
     * @param fileName The name of the file to create.
     */
    public static void createTopicWordFile(String fileName) {
        PrintWriter outputStreamWriterFiles = null;
        Scanner readerFiles = null;
        try{
            outputStreamWriterFiles = new PrintWriter(new FileOutputStream("input_topics_words.txt"));
            readerFiles = new Scanner(new FileInputStream(fileName));
            while(readerFiles.hasNextLine()){
                String lineTopic = readerFiles.nextLine();
                if(lineTopic.startsWith("#")){
                    String topic = lineTopic.substring(1);
                    outputStreamWriterFiles.print(topic+"\n");
                }
            }
            outputStreamWriterFiles.close();
            readerFiles.close();
        } catch (IOException e) {
            System.out.println("The file couldn't be created properly.");
        }
    }


    /**
     * General method for creating any required files by the application.
     * @param fileName The name of the file to be created.
     */
    public static void createFiles(String fileName){
        PrintWriter outputStreamWriterNewFiles = null;
        Scanner readerFilesTopics = null;
        Scanner readerInputFiles = null;
        try{
            readerFilesTopics = new Scanner(new File("input_topics_words.txt"));
            while (readerFilesTopics.hasNextLine()) {
                readerInputFiles = new Scanner(new File(fileName));
                String topic = readerFilesTopics.nextLine();
                outputStreamWriterNewFiles = new PrintWriter(new FileOutputStream(topic + ".txt"));
                boolean isTopicFound = false;
                while (readerInputFiles.hasNextLine()) {
                    String topicContent = readerInputFiles.nextLine();
                    if (topicContent.equals("#" + topic)) {
                        isTopicFound = true;
                        continue;
                    }
                    if (isTopicFound && topicContent.startsWith("#")) {
                        break;
                    }
                    if (isTopicFound) {
                        outputStreamWriterNewFiles.println(topicContent);
                    }
                }
                outputStreamWriterNewFiles.close();

            }
            readerFilesTopics.close();
            readerInputFiles.close();
        }catch (FileNotFoundException e){
            System.out.println("The file couldn't be created.");
        }
    }

    /**
     * Displays the main menu of the application to the user.
     */
    public static void displayMenu(){
        System.out.println(
                "--------------------------------------------------\n" +
                "Vocabulary Control Center\n" +
                "--------------------------------------------------\n" +
                "1 browse a topic\n" +
                "2 insert a new topic before another one\n" +
                "3 insert a new topic after another one\n" +
                "4 remove a topic\n" +
                "5 modify a topic\n" +
                "6 search topics for a word\n" +
                "7 load from a file\n" +
                "8 show all words starting with a given letter\n" +
                        "9 save to file\n"+
                "0 exit\n" +
                "--------------------------------------------------\n" +
                "Enter Your Choice:");
    }

    /**
     * Gets a validated choice from the user input within a certain range.
     * @param input Scanner object for reading the input from the user.
     * @return The validated choice as an integer.
     */
    public static int getValidChoice(Scanner input)
    {
        displayMenu();
        int choice = validIntegerInput(input);
        while (choice>9||choice<0)
        {
            System.out.print("\nThis is not a valid choice please try again: ");
            choice = validIntegerInput(input);
        }
        return choice;
    }



    public static void main(String []args){
        String fileTeacher = "A3_input_file.txt";
        String savedFile = "A3_saved_words.txt";
        list_vocab = null;
        Scanner input = new Scanner(System.in);
        createTopicWordFile(savedFile);
        createFiles(savedFile);
        int choice =0;
        do {
            choice = getValidChoice(input);
            switch (choice)
            {
                case 1:
                {
                    int choiceTopic=0;
                    if (list_vocab==null)
                    {
                        System.out.println("There are no items");
                    }
                    else {
                        do {
                            choiceTopic = getValidChoiceDisplayWords(input);

                            if (choiceTopic==0)
                            {
                                break;
                            }
                            else
                            {
                                displayWords(choiceTopic);
                            }
                        }
                        while (choiceTopic!=0);
                    }
                    break;
                }
                case 2:
                {
                    if (list_vocab==null)
                    {
                        System.out.println("There are no items");
                    }
                    else {
                        int choiceTopic = 0;
                        choiceTopic = getValidChoiceDisplayWords(input);
                        addTopicBeforeAnotherOne(input, choiceTopic);
                    }
                    break;
                }
                case 3:
                {
                    if (list_vocab==null)
                    {
                        System.out.println("There are no items");
                    }
                    else {
                        int choiceTopic = 0;
                        choiceTopic = getValidChoiceDisplayWords(input);
                        addTopicAfterAnotherOne(input, choiceTopic);
                    }
                    break;
                }
                case 4:
                {
                    if (list_vocab==null)
                    {
                        System.out.println("There are no items");
                    }
                    else {
                        int choiceTopic = 0;
                        choiceTopic = getValidChoiceDisplayWords(input);
                        removeTopic(choiceTopic);
                    }
                    break;
                }
                case 5:
                {
                    if (list_vocab==null)
                    {
                        System.out.println("There are no items");
                    }
                    else
                    {
                        int choiceTopic = getValidChoiceDisplayWords(input);
                        modifyTopic(input,choiceTopic);
                    }
                    break;

                }
                case 6:
                {
                    if (list_vocab==null)
                    {
                        System.out.println("There are no items");
                    }
                    else {
                        boolean found = false;
                        System.out.println("Enter the word you want to search: ");
                        String word = input.next();
                        DoublyLinkedList.Node current = list_vocab.getHead();
                        while (current.getNext() != null) {
                            if (current.getValue().getWords().contains(word)) {
                                System.out.println("The word \"" + word + "\" is in topic: " + current.getValue().getTopic());
                                found = true;
                                break;
                            } else {
                                found = false;
                            }
                            current = current.getNext();
                        }
                        if (found == false) {
                            System.out.println("The word \"" + word + "\" is not in any topic");
                        }
                    }
                    break;

                }
                case 7:
                {
                    System.out.println("Enter the name of the input file: ");
                    String name = input.next();
                    list_vocab = loadFiles(name);
                    if (list_vocab!=null) {
                        System.out.println("Done loading");
                    }
                    break;
                }
                case 8:
                {
                    if (list_vocab==null)
                    {
                        System.out.println("There are no items");
                    }
                    else
                    {
                        System.out.println("Enter the letter: ");
                        String strletter = getValidChoiceLetter(input);
                        char letter = strletter.charAt(0);
                        ArrayList <String> list = new ArrayList<String>();
                        DoublyLinkedList.Node current = list_vocab.getHead();
                        while (current != null) {

                            ArrayList<String> listTopic = current.getValue().getWords().startsWith(letter);
                            for(String elements: listTopic)
                            {
                                list.add(elements);
                            }
                            current = current.getNext();
                        }
                        //display
                        System.out.println("Here are all the words that start with\""+letter+"\"");
                        for(String elements: list)
                        {
                            System.out.println(elements+"\n");
                        }
                    }
                    break;

                }
                case 9:
                {
                    PrintWriter writer = null;
                    if (list_vocab==null)
                    {
                        System.out.println("There are no items");
                    }
                    else
                    {
                        try {
                            writer = new PrintWriter(new FileOutputStream("A3_saved_words.txt"));
                            DoublyLinkedList.Node current = list_vocab.getHead();
                            while (current!=null)
                            {
                                writer.println("#"+current.getValue().getTopic());

                                SinglyLinkedList.Node word = current.getValue().getWords().getHead();
                                while (word!=null)
                                {
                                    writer.println(word.getValue());
                                    word=word.getNext();
                                }
                                current=current.getNext();
                            }
                        }catch (FileNotFoundException e)
                        {
                            System.out.println("File not found");
                            break;
                        }
                        System.out.println("Your file has been successfully saved!");
                        writer.close();
                    }
                    break;
                }
            }
        }while (choice!=0);
    }

    static DoublyLinkedList list_vocab;


    /**
     * Loads files containing topics and words into a data structure.
     * @param fileName The name of the file to load.
     * @return A new DoublyLinkedList containing the loaded data.
     */
    public static DoublyLinkedList loadFiles(String fileName)
    {
        Scanner scanner = null;
        Scanner scanner2 = null;
        DoublyLinkedList list_vocabs = new DoublyLinkedList();
        String topic=null;
        SinglyLinkedList wordList= new SinglyLinkedList();

        try {
            scanner = new Scanner(new FileInputStream(fileName));
            while (scanner.hasNextLine()) {
                topic= scanner.nextLine();
                wordList.emptyList();
                scanner2 = new Scanner(new FileInputStream(topic+".txt"));
                while (scanner2.hasNextLine())
                {
                        wordList.addAtEnd(scanner2.nextLine());
                }
                list_vocabs.addAtTail(new Vocab(topic,wordList.clone()));
                wordList.emptyList();
            }
        }catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            return null;
        }
        return list_vocabs;
    }

    /**
     * Displays topics available in the application for user selection.
     */
    public static void displayTopics() {

        System.out.println("--------------------------------------------------\n" + "Pick a topic\n" + "--------------------------------------------------\n");
        DoublyLinkedList.Node current = list_vocab.getHead();
        int count =1;
        while (list_vocab.hasNext(current))
        {
            Vocab currentVocab = current.getValue();
            System.out.println(count+": "+currentVocab.getTopic());
            count++;
            current = current.getNext();
        }
        System.out.println("0 exit\n" +
                "--------------------------------------------------\n" +
                "Enter Your Choice:");
    }

    /**
     * Gets a validated numeric choice from the user specifically for word display options.
     * @param input Scanner object for reading the input from the user.
     * @return The validated choice as an integer.
     */
    public static int getValidChoiceDisplayWords(Scanner input)
    {
        displayTopics();
        int choice = validIntegerInput(input);
        while (choice>list_vocab.getSize()||choice<0)
        {
            System.out.print("\nThis is not a valid choice please try again: ");
            choice = validIntegerInput(input);
        }
        return choice;
    }

    /**
     * Gets a validated alphabetic choice from the user.
     * @param input Scanner object for reading the input from the user.
     * @return The validated letter choice as a String.
     */
    public static String getValidChoiceLetter(Scanner input)
    {
        String letter = input.next();
        while (letter.length()>1)
        {
            System.out.print("\nThis is not a valid choice please try again: ");
            letter = input.next();
        }
        return letter;
    }

    /**
     * Displays the word associated with the given choice from a doubly linked list.
     * The choice determines which node's value in the list is displayed.
     * @param choice The user's choice representing the position in the doubly linked list.
     */
    public static void displayWords(int choice)
    {
        int count = 1;
        DoublyLinkedList.Node current = list_vocab.getHead();
        while (count!=choice)
        {
            current = current.getNext();
            count++;
        }
        System.out.println(current.getValue());
    }

    /**
     * Adds a new topic and its associated words before another existing topic in the doubly linked list.
     * The topic and words are collected from the user input.
     * @param input The Scanner object to read user input.
     * @param choice The position in the list where the new topic will be inserted before.
     */
    public static void addTopicBeforeAnotherOne(Scanner input,int choice)
    {
        SinglyLinkedList listWords = new SinglyLinkedList();
        System.out.println("Enter the name of the topic: ");
        String trash = input.nextLine();
        String topic = input.nextLine();
        System.out.println("Enter a word - to quit press Enter: ");
        String word;
        while (!(word = input.nextLine()).isEmpty()) { // Read until an empty line is encountered
            listWords.addAtEnd(word);
            //System.out.println("Enter another word or press Enter to quit: ");
        }
        int count=1;
        DoublyLinkedList.Node current = list_vocab.getHead();
        while (count!=choice)
        {
            current = current.getNext();
            count++;
        }
        list_vocab.addBefore(current.getValue(),new Vocab(topic,listWords.clone()));
    }

    /**
     * Adds a new topic and its associated words after another existing topic in the doubly linked list.
     * The topic and words are collected from the user input.
     * @param input The Scanner object to read user input.
     * @param choice The position in the list where the new topic will be inserted after.
     */
    public static void addTopicAfterAnotherOne(Scanner input,int choice)
    {
        SinglyLinkedList listWords = new SinglyLinkedList();
        System.out.println("Enter the name of the topic: ");
        String trash = input.nextLine();
        String topic = input.nextLine();
        System.out.println("Enter a word - to quit press Enter: ");
        String word;
        while (!(word = input.nextLine()).isEmpty()) { // Read until an empty line is encountered
            listWords.addAtEnd(word);
            //System.out.println("Enter another word or press Enter to quit: ");
        }
        int count=1;
        DoublyLinkedList.Node current = list_vocab.getHead();
        while (count!=choice)
        {
            current = current.getNext();
            count++;
        }
        list_vocab.addAfter(current.getValue(),new Vocab(topic,listWords.clone()));
    }

    /**
     * Removes a topic from the doubly linked list based on the user's choice of position.
     * @param choice The position in the doubly linked list from which the topic will be removed.
     */
    public static void removeTopic(int choice)
    {
        SinglyLinkedList listWords = new SinglyLinkedList();
        int count=1;
        DoublyLinkedList.Node current = list_vocab.getHead();
        while (count!=choice)
        {
            current = current.getNext();
            count++;
        }
        list_vocab.removeValue(current.getValue());
    }


    /**
     * Prompts the user to enter a valid choice for modifying the topics, ensuring the choice is one of the allowed options.
     * @param input Scanner object for reading the input from the user.
     * @return A valid modification choice as a string.
     */
    public static String getValidModiFyChoice(Scanner input)
    {
        displayModifyMenu();
        System.out.print("Enter your choice: ");
        String choice = input.next();
        while (!choice.equals("r")&&!choice.equals("a")&&!choice.equals("c")&&!choice.equals("0"))
        {
            System.out.print("\nThis is not a valid choice please try again: ");
            choice = input.next();
        }
        return choice;
    }

    /**
     * Allows the user to modify a topic by adding, removing, or changing words.
     * The specific action is chosen by the user from a displayed menu.
     * @param input Scanner object for reading the input from the user.
     * @param choiceTopic The position of the topic in the doubly linked list to be modified.
     */
    //JE SUIS RENDUE LA
    public static void modifyTopic(Scanner input,int choiceTopic)
    {
        String choiceModify = "";
        int count =1;
        DoublyLinkedList.Node current = list_vocab.getHead();
        while (count!=choiceTopic)
        {
            current = current.getNext();
            count++;
        }
        do {
             choiceModify = getValidModiFyChoice(input);
            switch (choiceModify)
            {
                case "a":
                {
                    System.out.println("Enter a word: ");
                    String word = input.next();
                    if (current.getValue().getWords().contains(word)==false) {
                        current.getValue().getWords().addAtHead(word);
                    }
                    else
                    {
                        System.out.println("The word \""+word+"\" is already listed");
                    }
                    break;
                }
                case "r":
                {
                    displayWords(choiceTopic);
                    System.out.println("Enter a word: ");
                    String word = input.next();
                    if (current.getValue().getWords().contains(word)==false) {
                        System.out.println("Sorry there is no word "+"\""+word+"\"");
                    }
                    else
                    {
                        current.getValue().getWords().removeValue(word);
                    }
                    break;
                }
                case "c":
                {
                    displayWords(choiceTopic);
                    System.out.println("Enter the word to change: ");
                    String word = input.next();
                    if (current.getValue().getWords().contains(word)==false) {
                        System.out.println("Sorry there is no word "+"\""+word+"\"");
                    }
                    else
                    {
                        System.out.println("Enter the new word: ");
                        String newWord = input.next();
                        current.getValue().getWords().modifyValue(word,newWord);
                    }
                    break;
                }
            }

        }while (!choiceModify.equals("0"));
    }

    /**
     * Displays a menu for modifying topics, with options to add, remove, or change words, or to exit the modification menu.
     */
    public static void displayModifyMenu()
    {
        System.out.println("-----------------------------\n" +
                "Modify Topics Menu\n" +
                "-----------------------------\n" +
                "a add a word\n" +
                "r remove a word\n" +
                "c change a word\n" +
                "0 Exit\n" +
                "-----------------------------\n");
    }
}

