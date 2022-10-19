import java.util.Arrays;
import java.util.Random;

/**
 * An array-based demonstration for FIFO and LIFO operations
 * @version 202210142330
 */

public class SimpleXIFO {

    /**
     * An array that stores strings added to it either on a first-available
     * basis (using the addLast) method; or inserted as the first element
     * (using the addFirst method).
     */
    private String[] xifo;


    /** A counter that tells us when the xifo array is full or empty */
    private int usage;


    /** Default size for the array if someone calls the default constructor */
    private static final int DEFAULT_SIZE = 4;


    /**
     * Default constructor. Passes the default size value to the basic constructor.
     *
     * DO NOT MODIFY THIS CONSTRUCTOR
     */
    public SimpleXIFO() {
        this(DEFAULT_SIZE);
    }  // Default constructor ... DO NOT MODIFY


    /**
     * Basic constructor. Initializes an array with the specified size and sets
     * its usage to 0.
     *
     * @param size int value of size for the xifo array.
     *
     * DO NOT MODIFY THIS CONSTRUCTOR
     */
    public SimpleXIFO(int size) {
        this.xifo = new String[size];
        this.usage = 0;
    }  // Basic constructor ... DO NOT MODIFY


    /**
     * Last-in.
     *
     * Method adds a string to the first available position in the array, if there
     * is room in the array. Note that the method does not add elements at the
     * last position in the array but at the first available. As more elements
     * are added, they move closer and closer to the end fo the array.
     *
     * @param string String value to the end of the array
     */
    public void addLast(String string) {
        // Check that there is room in the array
        if (this.usage < this.xifo.length) {
            this.xifo[usage] = string;
            // Update usage
            usage++;
        }
    }  // method addLast


    /**
     * First out.
     *
     * Method removes the first null element of the array.
     *
     * An index will traverse an array until a null value is found. When found,
     * store the value of the array at the current index then set the array at
     * the current index to null.
     *
     * @return String with the value of the first non-null element in the array.
     */
    public String removeFirst() {
        //declare and initialize traverse variable to 0. Used to go through the array.
        int traverse = 0;
        //While a null value is encountered in the array at traverse, increment traverse by one to know where to delete a value
        while (this.xifo[traverse] == null){
            traverse++;
        }

        //Store the value to be deleted in storage.
        String storage = this.xifo[traverse];
        //Set the first element that is not null to null.
        this.xifo[traverse] = null;
        //Return stored value.
        return storage;
    }  // method removeFirst


    /**
     * First in
     *
     * Adds an element at the beginning of the array, if there is room in the
     * array. The method must ensure that existing elements in the array are not
     * lost. For example, consider the array ["C", "B", "A", null]. If we use
     * addFirst("D"), the array must become ["D", "C", "B", "A"].
     *
     * The addFirst Method first checks if there is space to add an element in the array.
     * If there is space then all values will be shifted to the next element until a null is reached.
     * When a null is reached, the null value will be replaced with the element prior to it and all
     * values after null will not change. The value of the first element of the array will then be
     * updated to be the value wished to be added.
     *
     * @param string String value to add to the beginning of the array.
     */
    public void addFirst(String string) {
        //traverse variable
        int traverse = 0;
        //declare and initialize a variable to true. This variable will be false when there is no null value in the array.
        boolean checker = true;
        //declare and initialize a variable to false. This variable will be true if there is a null value in the array.
        boolean space = false;

        //while no null value has been found yet in the array, the loop should iterate.
        while (checker) {
            //if the array at the point being evaluated is null, there is space in the array and this loop should be done executing.
            if (this.xifo[traverse] == null) {
                space = true;
                checker = false;
            }
            //otherwise if we have been through the whole array and not found any null value, there is no space and the loop should stop executing.
            else if (traverse == this.xifo.length - 1){
                checker = false;
            }
            //increment traverse until either a null value is found or no null value is found when at the end of the array.
            traverse++;
        }

        //if there is space in the array
        if (space) {
            //declare and initialize an index
            int index = 0;
            //declare and initialize boolean to represent if a null value has been reached
            boolean isNullReached = false;
            //declare and initialize a String to be the value of the array at index.
            String storage = this.xifo[index];
            //while we have not reached a null value
            while (!isNullReached) {
                //if this is the first time the loop has iterated
                if (index == 0) {
                    //if the first element of the array is null, set is null reached to true to stop executing the loop.
                    if (this.xifo[index] == null){
                        isNullReached = true;
                    }
                    //increment the index by one
                    index++;
                }
                else if (index < this.xifo.length) {
                    //if the array at the second slot is null, set isNullReached to true.
                    if (index == 1 && this.xifo[index] == null){
                        isNullReached = true;
                    }
                    //create a temporary String to hold the contents of the array at index.
                    String temp = this.xifo[index];
                    //Set the array at index equal to the stored value.
                    this.xifo[index] = storage;
                    //Update storage to be equal to the temporary string.
                    storage = temp;
                    //Increment the index by one.
                    index++;
                    //If a null value has been reached post-increment of index
                    if (this.xifo[index] == null){
                        //set is null reached to true
                        isNullReached = true;
                        //set the array at index to the temporary stored value
                        this.xifo[index] = temp;
                    }
                }
                //otherwise if a null value is encountered in the aray at index
                else if (this.xifo[index] == null){
                    //set isNullReached to true
                    isNullReached = true;
                }
            }
            //Set the first value of the array equal to the String parameter.
            this.xifo[0] = string;
        }
    }// method addFirst


    /** For testing */
    public String[] getXifo() {
        return this.xifo;
    }  // method getXifo ... DO NOT MODIFY


    /** For test */
    public String toString() {
        return Arrays.toString(this.xifo);
    }  // method toString ... DO NOT MODIFY


    /**
     * For testing purposes
     */
    public static void main(String[] args) {
        int testSize = 10;
        int ascii_A = (int) 'A';
        int letters = 26;
        Random rng = new Random();
        SimpleXIFO fifo = new SimpleXIFO(testSize);
        SimpleXIFO lifo = new SimpleXIFO(testSize);
        for (int i = 0; i < testSize; i++) {
            String dataToAdd = String.valueOf((char) (ascii_A+rng.nextInt(letters)));
            fifo.addFirst(dataToAdd);
            lifo.addLast(dataToAdd);
        }
        String[] ofif = fifo.getXifo().clone();
        String[] ofil = lifo.getXifo().clone();
        boolean fifoTest = true;
        boolean lifoTest = true;
        for (int i = 0; i < testSize; i++) {
            fifoTest = fifoTest && fifo.removeFirst().equals(ofif[i]);
            lifoTest = lifoTest && lifo.removeFirst().equals(ofil[i]);
        }
        boolean symmetry = true;
        for (int i = 0; i < testSize; i++) {
            symmetry = symmetry && ofil[i].equals(ofif[ofif.length-1-i]);
        }
        if (symmetry && fifoTest && lifoTest) {
            System.out.printf("\nYour code seems to be working as expected.\n");
        } else if (!symmetry) {
            System.out.printf("\nYour addFirst method is not quite there yet.\n");
        } else {
            System.out.printf("\nYour removeFirst method is not quite there yet.\n");
        }
    }  // method main ... DO NOT MODIFY
}