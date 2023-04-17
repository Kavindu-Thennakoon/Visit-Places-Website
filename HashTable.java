import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HashTable {
    private String[] hashArray;
    private int size;
    private int count;

    public HashTable(int size) {
        this.size = size;
        hashArray = new String[size];
        count = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCount() {
        return count;
    }

    private int hash(String word) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                sum += (int) c - 97;
            } else if (c >= 'A' && c <= 'Z') {
                sum += (int) c - 39;
            }
        }
        return sum;
    }

    public void insert(String word) {
        int hashValue = hash(word);
        int index = hashValue % size;
        int i = 1;

        while (hashArray[index] != null) {
            index = (index + i * i) % size;
            i++;
        }

        hashArray[index] = word;
        count++;

        if (count >= size * 0.9) {
            resize();
        }
    }

    private void resize() {
        String[] temp = hashArray;
        size *= 2;
        hashArray = new String[size];
        count = 0;

        for (String word : temp) {
            if (word != null) {
                insert(word);
            }
        }
    }

    public String search(String word) {
        int hashValue = hash(word);
        int index = hashValue % size;
        int i = 1;
        while (hashArray[index] != null) {
            if (hashArray[index].equals(word)) {
                return hashArray[index];
            }
            index = (index + i * i) % size;
            i++;
        }
        return null;
    }

    public void printHashTable(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new File(fileName));
            for (String word : hashArray) {
                if (word != null) {
                    writer.println(word);
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // create a new hash table with size 10
        HashTable table = new HashTable(10);

        // insert some words into the hash table
        table.insert("hello");
        table.insert("world");
        table.insert("example");
        table.insert("java");
        table.insert("programming");

        // print the size and count of the hash table
        System.out.println("Size: " + table.getSize());
        System.out.println("Count: " + table.getCount());

        // search for a word in the hash table
        String searchWord = "java";
        String result = table.search(searchWord);
        if (result == null) {
            System.out.println(searchWord + " not found in the hash table");
        } else {
            System.out.println(searchWord + " found in the hash table");
        }
    }

    public void readInputFile(String inputFile) {
    }

    public void writeOutputFiles(String outputFile1, String outputFile2) {
    }

}
