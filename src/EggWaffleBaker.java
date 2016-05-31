package avs.models;

public class EggWaffleBaker implements Runnable{
	private Thread thread;
	private Tower tower;

	public EggWaffleBaker(){
		thread = new Thread(this);
	}

	public void run(){
		Game.getInstance().createEggWaffle(tower);
	}

	public void start(){
		this.thread.start();
	}

	public Thread getThread(){
		return this.thread;
	}	
}
