package tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		TnakFrame frame = new TnakFrame();
		int initTankCount = PropertyManger.getInt("initTankCount");
		for (int i = 0; i < initTankCount; i++) {
			frame.tanks.add(new Tank(100+i*50, 100, Direction.DOWN,Group.BAD, frame));
		}
		while(true){
		Thread.sleep(100);
		frame.repaint();
		}
	}

}
