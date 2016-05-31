package avs.models;

public class ZombieSummoner implements Runnable{
	private Thread thread;

	public ZombieSummoner(){
		this.thread = new Thread(this);
	}

	public void run(){
        try {
            while (true) {
                Game.getInstance().createZombie();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
	}

	public void start(){
		this.thread.start();
	}
}
