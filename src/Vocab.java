
public class Vocab {
    private String topic;

    private SinglyLinkedList words;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setWords(SinglyLinkedList words) {
        this.words = words;
    }

    public SinglyLinkedList getWords()
    {
        return words;
    }

    /**
     * Constructs a Vocab object with a specified topic and a list of words.
     * @param topic The topic of the vocabulary.
     * @param words A SinglyLinkedList containing words associated with the topic.
     */
    public Vocab(String topic, SinglyLinkedList words) {
        this.topic = topic;
        this.words = words;
    }

    /**
     * Constructs a copy of an existing Vocab object.
     * @param other The Vocab object to copy.
     */

    public Vocab(Vocab other) {
        this.topic = other.topic;
        this.words = other.words;
    }

    /**
     * Compares this Vocab object to another object to determine equality.
     * The comparison is based on the topic only.
     * @param other The object to compare with this Vocab.
     * @return true if the topics are the same, false otherwise.
     */

    public boolean equals(Object other)
    {
        if (other ==null)
        {
            return false;
        }
        else if(getClass()!=other.getClass())
        {
            return false;
        }
        else
        {
            Vocab otherV = (Vocab) other;
            return this.topic.equals(otherV.topic);
        }
    }

    /**
     * Returns a string representation of this Vocab object, typically presenting the topic and words.
     * @return A string representation of this Vocab.
     */

    public String toString()
    {
        return "Topic: "+this.topic+"\nwords:\n"+words.displayList();
    }
}
