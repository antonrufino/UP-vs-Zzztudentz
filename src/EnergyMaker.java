package avs.models;

public class EnergyMaker implements Runnable{
	private Thread thread;
    private int delay;

	public EnergyMaker(){
		thread = new Thread(this);
	}

	public void run(){
        try {
            while (true) {
                Game.getInstance().createEnergy();
                Thread.sleep(10000);
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