public class Example {
  public static void main(String[] args) throws InterruptedException {
    Thread bo = new Thread(new BoThread());
    long start = System.currentTimeMillis();

    bo.start();

    while (bo.isAlive()) {
      System.out.println(Thread.currentThread().getName() + ": waiting...");
      bo.join(1000);

      if (System.currentTimeMillis() - start > 7000 && bo.isAlive()) {
        System.out.println(Thread.currentThread().getName() + ": tired!!!");
        bo.interrupt();
        bo.join();
      }
    }
  }
}

class BoThread implements Runnable {
  public void run() {
    String[] steps = new String[] {
      "Step - 1", "Step - 2", "Step - 3", "Step - 4"
    };

    try {
      for (String step : steps) {
        System.out.println(Thread.currentThread().getName() + ": " + step);
        Thread.sleep(3000);
      }
    } catch (InterruptedException ie) {
      System.out.println(Thread.currentThread().getName() + ": interrupted");
    }
  }
}