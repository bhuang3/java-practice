public class SleepBo {
  public static void main(String[] args) throws InterruptedException {
    ThreadBo tb = new ThreadBo();

    String[] messages = new String[] {
      "Message - 1", "Message - 2", "Message - 3", "Message - 4"
    };

    tb.start();

    for (String message : messages) {
      Thread.sleep(1000);
      System.out.println(message);
    }
  }
}