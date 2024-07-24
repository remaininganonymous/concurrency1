import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final int maxSize;
    private final Queue<T> queue;

    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.remove();
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}

