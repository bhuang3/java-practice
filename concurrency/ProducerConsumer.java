import java.util.ArrayDeque;
import java.util.Deque;

public class ProducerConsumer {
  public static void main(String[] args) throws InterruptedException {
    Bucket bucket = new Bucket();

    new Thread(new Producer(bucket)).start();
    Thread.sleep(3333);
    new Thread(new Consumer(bucket)).start();
  }
}

class Bucket {
  Deque<String> items = new ArrayDeque<String>();

  public synchronized void add(String item) {
    if (items.size() >= 5) {
      try {
        System.out.println("Too many, waiting");
        wait();
      } catch (InterruptedException ie) {
        System.out.println("Interrupted!");
      }
    }

    items.addLast(item);

    System.out.println("Add item - total = " + items.size());

    notifyAll();
  }

  public synchronized String pick() {
    if (items.size() <= 0) {
      try {
        System.out.println("Too few, waiting");
        wait();
      } catch (InterruptedException ie) {
        System.out.println("Interrupted!!");
      }
    }

    notifyAll();

    System.out.println("Pick item - total = " + items.size());

    return items.pollFirst();
  }
}

class Producer implements Runnable {
  private Bucket bucket;

  public Producer(Bucket bucket) {
    this.bucket = bucket;
  }

  public void run() {
    while (true) {
      this.bucket.add(System.currentTimeMillis() + "");

      try {
        Thread.sleep((long) (1000 * Math.random()));
      } catch (InterruptedException ie) {
        return;
      }
    }
  }
}

class Consumer implements Runnable {
  private Bucket bucket;

  public Consumer(Bucket bucket) {
    this.bucket = bucket;
  }

  public void run() {
    while (true) {
      this.bucket.pick();

      try {
        Thread.sleep((long) (2000 * Math.random()));
      } catch (InterruptedException ie) {
        return;
      }
    }
  }
}