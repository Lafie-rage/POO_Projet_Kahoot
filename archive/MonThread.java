package archive;

public class MonThread extends Thread{

    @Override
    public void run() {
        System.out.println("Serveur up");
        while(!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(1000);
                System.out.println("Serveur en vie");
            } catch (InterruptedException e) {
               this.interrupt();
            }

        }
        this.interrupt();
    }

}