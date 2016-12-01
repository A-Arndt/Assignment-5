
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        HashTable h = new HashTable();
        ArrayList<String> names = new ArrayList<String>();
        Scanner sc = null;
        int arraySize;
        try {
            sc = new Scanner(new FileReader("loadsOfWords.txt"));

            while (sc.hasNext()) {
                String cur = sc.nextLine();
                names.add(cur);
            }
        } catch (FileNotFoundException ex){
            //except it
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        arraySize = names.size();
        long end = System.currentTimeMillis() + 1000;
        int j = 0;
        while (System.currentTimeMillis() < end) {
            h.add(names.get(j));
            j++;
        }
        System.out.println(j + " at " + arraySize);

        long start = System.currentTimeMillis();
        for (int i = 0; 10000 > i; i++) {
            h.find(names.get(i));
        }
        end = System.currentTimeMillis() ;
        long totalTime = end - start;
        double secondsForOp = totalTime / 10000.0 /1000.0;
        System.out.println(secondsForOp + " at " + j);


        HashTable h2 = new HashTable();
        int size = 200;
        while (size <= 200) {
            for (int i = 0; i < size; i++) {
                h2.add(names.get(i));
            }
            start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                h2.find(names.get(i % size));
            }
            end = System.currentTimeMillis();
            totalTime = end - start;
            System.out.println(totalTime + " " + "at " + size);
            size += 200;
        }

    }

}
