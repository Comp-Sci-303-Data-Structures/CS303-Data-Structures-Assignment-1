import java.io.*;
import java.util.Scanner;

//Yenghoua Lee
//Comp Sci 303
//Programming Assignment 1

public class ArrayOperations{
    private int[] array;
    private int size;
    private int capacity;
    
    // Constructor
    public ArrayOperations(int initialCapacity) {
        this.capacity = initialCapacity;
        this.array = new int[capacity];
        this.size = 0;
    }
        

public void readFromFile(String filename, int[] arr) {
        try{
            Scanner fileScanner = new Scanner (new File(filename));
            while (fileScanner.hasNextInt() && size < capacity) {
                array[size++] = fileScanner.nextInt();  //Scan and store the values in the array and increment the size
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

public void modifyArray(int[] arr, int index, int value) throws IndexOutOfBoundsException {
    if (index >= 0 && index < size) {
        arr[index] = value;
    } else {
        throw new IndexOutOfBoundsException("Index out of bounds.");
    }
}

public int searchInteger(int value) {
    for (int i = 0; i < size; i++) {
        if (array[i] == value) {
            System.out.println("Value found at index: " + i);
            return i; //return index if found
        }
    }
    return -1; //Value not found
}

public String modifyArray(int index, int newValue) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index out of bounds.");
    }
    int oldValue = array[index];
    array[index] = newValue;
    return "Modified index " + index + " from " + oldValue + " to " + newValue;
} 

public void addtoArray(int value) throws IndexOutOfBoundsException {
    if (size >= capacity) {
        throw new IndexOutOfBoundsException("Array is full! No more elements can be added.");
    }
    array[size++] = value;
    System.out.println("Successfully added " + value + " to the array.");
}
    

public int removeFromArray(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index out of bounds.");
    }
    int removedValue = array[index];
    // Shift elements to the left to fill the gap
    for (int i = index; i < size - 1; i++) {
        array[i] = array[i + 1];
    }
    size--;
    System.out.println("Successfully removed element " + removedValue + " at index " + index);
    return removedValue;
}

public void displayArray() {
        System.out.print("Array contents: [");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
// Gets the currennt size of the Array
public int getSize() {
     return size;
 }
 public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayOperations arrayOps = new ArrayOperations(150); // initialize the capacity of the array to 100

        System.out.println("Enter the filename to read from:"); // prompt user to enter filename
        String filename = scanner.nextLine();
        arrayOps.readFromFile(filename, arrayOps.array); // read the file and stores the values in the array
        
        boolean running = true;
        
        while (running) {
            System.out.println("\n=========Array Operations Menu==========");
            System.out.println("1. Search for an Integer");
            System.out.println("2. Modify element in Array");
            System.out.println("3. Add to Array");
            System.out.println("4. Remove an element from Array");
            System.out.println("5. Display Array");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
           
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Q1.1 - Prompt user to search for an integer in the array
                    System.out.print("Enter integer to search: ");
                    int searchValue = scanner.nextInt();
                    int index = arrayOps.searchInteger(searchValue);
                    if (index == -1) {
                        System.out.println("Integer " + searchValue + " not found in array.");
                    }
                    break;

                case 2:
                    // Q1.2 & Q2 - Modify with exception handling
                    try {
                        System.out.print("Enter index to modify: ");
                        int indexToModify = scanner.nextInt();
                        System.out.print("Enter new value: ");
                        int newValue = scanner.nextInt();

                        String result = arrayOps.modifyArray(indexToModify, newValue);
                        System.out.println(result);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: Invalid input. Please enter valid integers.");
                        scanner.nextLine(); // Clear buffer
                    }
                    break;

                case 3:
                    // Add integer with exception handling
                    try {
                        System.out.print("Enter integer to add: ");
                        int addValue = scanner.nextInt();
                        
                        arrayOps.addtoArray(addValue);
                        
                    } catch (IllegalStateException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: Invalid input. Please enter a valid integer.");
                        scanner.nextLine(); // Clear buffer
                    }
                    break;
                    
                case 4:
                    // Remove integer
                    System.out.print("Enter index to remove: ");
                    int removeIndex = scanner.nextInt();
                    int removedValue = arrayOps.removeFromArray(removeIndex);
                    
                    if (removedValue != -1) {
                        System.out.println("Successfully removed " + removedValue + " from index " + removeIndex);
                    }
                    break;
                    
                case 5:
                    // Display array
                    arrayOps.displayArray();
                    System.out.println("Current size: " + arrayOps.getSize());
                    break;
                    
                case 6:
                    // Exit
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }  
        scanner.close();
    }
}
