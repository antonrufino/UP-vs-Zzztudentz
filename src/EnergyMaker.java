package avs.models;

public class EnergyMaker implements Runnable{
	private Thread thread;

	public EnergyMaker(){
		thread = new Thread(this);
	}

	public void run(){
		Game.getInstance().createEnergy();
	}

	public void start(){
		this.thread.start();
	}
}
