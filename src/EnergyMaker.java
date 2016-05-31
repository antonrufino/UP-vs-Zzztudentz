package avs.models;

public class EnergyMaker implements Runnable{
	private Thread thread;
    private int delay;
    private Kopiko kopiko;

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
            return;
        }
	}

	public void start(){
		this.thread.start();
	}

    public void stop() {
        this.thread.interrupt();
    }
}
