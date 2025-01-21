import java.util.Arrays;

/** a queue class that uses a one-dimensional array */

public class ArrayQueue implements Queue
{
   // data members
   int front;          // one counterclockwise from first element
   int rear;           // position of rear element of queue
   Object [] queue;    // element array

   // constructors
   /** create a queue with the given initial capacity */
   public ArrayQueue(int initialCapacity)
   {
      if (initialCapacity < 1)
         throw new IllegalArgumentException
               ("initialCapacity must be >= 1");
      queue = new Object [initialCapacity + 1];
      // default front = rear = 0
   }

   /** create a queue with initial capacity 10 */
   public ArrayQueue()
   {// use default capacity of 10
      this(10);
   }

   // methods
   /** @return true iff queue is empty */
   public boolean isEmpty()
      {return front == rear;}

   // size
   public int size() {
      return (rear - front + queue.length) % queue.length;
  }
  

   /** @return front element of queue
     * @return null if queue is empty */
   public Object getFrontElement()
   {
      if (isEmpty())
         return null;
      else
         return queue[(front + 1) % queue.length];
   }

   /** @return rear element of queue
     * @return null if the queue is empty */
   public Object getRearElement()
   {
      if (isEmpty())
         return null;
      else
         return queue[rear];
   }

   /** insert theElement at the rear of the queue */
   public void put(Object theElement)
   {
      // increase array length if necessary
      if ((rear + 1) % queue.length == front)
      {// double array size
         // allocate a new array
         Object [] newQueue = new Object [2 * queue.length];

         // copy elements into new array
         int start = (front + 1) % queue.length;
         if (start < 2)
            // no wrap around
            System.arraycopy(queue, start, newQueue, 0,
                             queue.length - 1);
         else
         {  // queue wraps around
            System.arraycopy(queue, start, newQueue, 0,
                             queue.length - start);
            System.arraycopy(queue, 0, newQueue,
                             queue.length - start, rear + 1);
         }

         // switch to newQueue and set front and rear
         front = newQueue.length - 1;
         rear = queue.length - 2;   // queue size is queue.length - 1
         queue = newQueue;
      }

      // put theElement at the rear of the queue
      rear = (rear + 1) % queue.length;
      queue[rear] = theElement;
   }

   /** remove an element from the front of the queue
     * @return removed element
     * @return null if the queue is empty */
   public Object remove()
   {
      if (isEmpty())
         return null;
      front = (front + 1) % queue.length;
      Object frontElement = queue[front];
      queue[front] = null;   // enable garbage collection
      return frontElement;
   }



   /***********************************************************************/
   	// reverse
	public void reverse(int i) {
		if (i < 0 || i >= size()) {
			throw new IllegalArgumentException("Индекс боломжит хязгаарт байх ёстой.");
		}
		int start = front + 1; // эхний элемент
		int end = front + 1 + i; // i индекстэй элемент
		while (start < end) {
			Object temp = queue[start % queue.length];
			queue[start % queue.length] = queue[end % queue.length];
			queue[end % queue.length] = temp;
			start++;
			end--;
		}
	}

	// clone
	public ArrayQueue clone() {
		ArrayQueue newQueue = new ArrayQueue(queue.length - 1);
		for (int j = 0; j < size(); j++) {
			newQueue.put(queue[(front + 1 + j) % queue.length]);
		}
		return newQueue;
	}
	
	// clear

	public void clear() {
		front = 0;
		rear = 0;
	}

	// toArray
	public Object[] toArray() {
		Object[] array = new Object[size()];
		for (int j = 0; j < size(); j++) {
			array[j] = queue[(front + 1 + j) % queue.length];
		}
		return array;
	}

	// search
	public int search(Object key) {
		for (int j = 0; j < size(); j++) {
			if (queue[(front + 1 + j) % queue.length].equals(key)) {
				return j;
			}
		}
		return -1; // Олдоогүй бол
	}

	//update
	public void update(Object element, Object newElement) {
		for (int j = 0; j < size(); j++) {
			if (queue[(front + 1 + j) % queue.length].equals(element)) {
				queue[(front + 1 + j) % queue.length] = newElement;
				return;
			}
		}
		throw new IllegalArgumentException("Элемент олдсонгүй.");
	}
	
	//sort


	public void sort() {
	    Object[] array = toArray();
	    Arrays.sort(array); // Өсөх дарааллаар эрэмбэлнэ
	    clear();
	    for (Object obj : array) {
	        put(obj); // Шинээр эрэмбэлсэн дарааллаар оруулах
	    }
	}
   
   /** test program */
   public static void main(String [] args)
   {  
      int x;
      ArrayQueue q = new ArrayQueue(3);
      // add a few elements
      q.put(new Integer(1));
      q.put(new Integer(2));
      q.put(new Integer(3));
      q.put(new Integer(4));

      // remove and add to test wraparound array doubling
      q.remove();
      q.remove();
      q.put(new Integer(5));
      q.put(new Integer(6));
      q.put(new Integer(7));
      q.put(new Integer(8));
      q.put(new Integer(9));
      q.put(new Integer(10));
      q.put(new Integer(11));
      q.put(new Integer(12));

      // delete all elements
      while (!q.isEmpty())
      {
         System.out.println("Rear element is " + q.getRearElement());
         System.out.println("Front element is " + q.getFrontElement());
         System.out.println("Removed the element " + q.remove());
      }
   }  
}
