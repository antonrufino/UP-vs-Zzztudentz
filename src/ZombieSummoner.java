package avs.models;

public class ZombieSummoner implements Runnable{
	private Thread thread;

	public ZombieSummoner(){
		this.thread = new Thread(this);
	}

	public void run(){
		Game.getInstance().createZombie(5,5);
	}

	public void start(){
		this.thread.start();
	}
}