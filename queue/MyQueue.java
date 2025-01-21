import java.sql.Array;
import java.util.Arrays;

public class MyQueue  extends ArrayQueue{



	

	public static void main(String[] args) {
		ArrayQueue queue = new ArrayQueue(10);

        // Элементийн нэмэх
        queue.put(5);
        queue.put(1);
        queue.put(3);
        queue.put(8);
        System.out.println("Анхны Queue: " + java.util.Arrays.toString(queue.toArray()));

        // reverse
        queue.reverse(2);
        System.out.println("Reverse хийгдсэн Queue: " + java.util.Arrays.toString(queue.toArray()));

        // clone
        ArrayQueue clonedQueue = queue.clone();
        System.out.println("Clone Queue: " + java.util.Arrays.toString(clonedQueue.toArray()));

        // clear
        queue.clear();
        System.out.println("Clear хийсний дараа Queue хоосон уу? " + queue.isEmpty());

        // sort
        clonedQueue.sort();
        System.out.println("Эрэмбэлэгдсэн Queue: " + java.util.Arrays.toString(clonedQueue.toArray()));

        // search
        System.out.println("Элемент 3-ын байрлал: " + clonedQueue.search(3));

        // update
        clonedQueue.update(3, 10);
        System.out.println("Update хийсний дараах Queue: " + java.util.Arrays.toString(clonedQueue.toArray()));
    

	}
}
