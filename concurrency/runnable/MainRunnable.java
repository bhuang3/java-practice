public class MainRunnable {
  public static void main(String[] args) {
    Thread t = new Thread(new RunnableBo());

    t.start();
  }
}