package hashtable;

import java.io.*;
import java.util.*;

public class MyHashTable {
    private static class HashEntry {
        protected Object key;
        protected Object element;

        private HashEntry(Object key, Object element) {
            this.key = key;
            this.element = element;
        }
    }

    private int divisor;
    private HashEntry[] table;
    private int size;

    public MyHashTable(int divisor) {
        this.divisor = divisor;
        this.table = new HashEntry[divisor];
        this.size = 0;
    }

    public void readFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    add(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    

    public void add(Object key, Object element) {
        int index = search(key);
        if (table[index] == null) {
            table[index] = new HashEntry(key, element);
            size++;
        } else {
            table[index].element = element; // Update element if key exists
        }
    }

    public void remove(Object key) {
        int index = search(key);
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            size--;
        } else {
            System.out.println("Key not found: " + key);
        }
    }

    private int search(Object theKey) {
        int i = Math.abs(theKey.hashCode()) % divisor; // Home bucket
        int j = i; // Start at home bucket
        do {
            if (table[j] == null || table[j].key.equals(theKey)) {
                return j; // Return index
            }
            j = (j + 1) % divisor; // Next bucket
        } while (j != i); // Returned to home bucket?
    
        return -1; // If key not found
    }



    public Object get(Object key) {
        int index = (int) search(key);
        if (index != -1 && table[index] != null) {
            return table[index].element;
        }
        return null;
    }

    public void updateElement(Object key, Object element) {
        int index = (int) search(key);
        if (table[index] != null) {
            table[index].element = element;
        } else {
            System.out.println("Key not found for update: " + key);
        }
    }

    public void updateKey(Object oldKey, Object newKey) {
        int index = (int) search(oldKey);
        if (table[index] != null) {
            HashEntry entry = table[index];
            table[index] = null; // Remove old entry
            add(newKey, entry.element);
        } else {
            System.out.println("Key not found for update: " + oldKey);
        }
    }

    public void output() {
        for (int i = 0; i < divisor; i++) {
            if (table[i] == null) {
                System.out.println("null");
            } else {
                System.out.println(table[i].key + " = " + table[i].element);
            }
        }
    }

    public static void main(String[] args) {
        MyHashTable myTable = new MyHashTable(11);

        // Test readFromFile
        myTable.readFromFile("c:\\Users\\chinzorig.otgonjarga\\java\\dsa\\hashtable\\dictionary.txt");

        // Test other methods
        myTable.add("apple", "A fruit");
        myTable.add("banana", "Another fruit");
        myTable.output();

        myTable.updateElement("apple", "A tasty fruit");
        myTable.output();

        myTable.updateKey("banana", "yellow fruit");
        myTable.output();

        myTable.remove("apple");
        myTable.output();
    }
}
