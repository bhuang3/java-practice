public class ThreadBo extends Thread {
  public void run () {
    try {
      Thread.sleep(1500);
    } catch (InterruptedException ie) {
      
    }

    System.out.println("Bo - Thread");
  }
}