package tank;

import java.awt.Graphics;

public class Explode {

	public static int WIDTH = ResourceManager.explodes[0].getWidth();
	public static int HEIGHT = ResourceManager.explodes[0].getHeight();
	private int x,y;
	private boolean living = true;
	TnakFrame frame = null;
	private int step = 0;
	
	public Explode(int x,int y,TnakFrame frame){
		this.x = x;
		this.y = y;
		this.frame = frame;
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}
	
	public void paint(Graphics g){
		g.drawImage(ResourceManager.explodes[step++], x, y, null);
		if (step>=ResourceManager.explodes.length) {
			frame.explodes.remove(this);
		}
	}
}
