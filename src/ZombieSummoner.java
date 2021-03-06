package avs.models;

public class ZombieSummoner implements Runnable{
	private Thread thread;
	private boolean isHugeWave;

	public ZombieSummoner(){
		this.thread = new Thread(this);
	}

	public void run(){
		try {
            Thread.sleep(30000);
            while (true) {
            	if(!isHugeWave){
            		Game.getInstance().createZombie();
               		Thread.sleep(10000);
            	}else{
            		Thread.sleep(3000);
            		for(int i = 0; i < 10; i++){
            			Game.getInstance().createZombie();
               			Thread.sleep(3000);
            		}
            		isHugeWave = false;
            	}
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

	public void setIsHugeWave(boolean isHugeWave){
		this.isHugeWave = isHugeWave;
	}

    public boolean getIsHugeWave(){
        return this.isHugeWave;
    }
}
