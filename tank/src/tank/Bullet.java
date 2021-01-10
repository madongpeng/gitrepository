package tank;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 炮弹类
 */
public class Bullet {
	
	private static final int SPEED = PropertyManger.getInt("bulletSpeed");
	public static int WIDTH = ResourceManager.bulletU.getWidth();
	public static int HEIGHT = ResourceManager.bulletU.getHeight();
			
	private int x,y;
	private Direction direction;
	private Group group = Group.BAD;
	private boolean living = true;
	Rectangle rectangle = new Rectangle();
	TnakFrame frame = null;
	
	public Bullet(int x,int y,Direction direction,Group group,TnakFrame frame){
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.group = group;
		this.frame = frame;
		rectangle.x = this.x;
		rectangle.y = this.y;
		rectangle.width = WIDTH;
		rectangle.height = HEIGHT;
	}
	
	public void paint(Graphics g){
		if (!living) frame.bullets.remove(this);
		switch (direction) {
		case UP:
			g.drawImage(ResourceManager.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceManager.bulletD, x, y, null);
			break;
		case LEFT:
			g.drawImage(ResourceManager.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceManager.bulletR, x, y, null);
			break;	
		}
		
		move();
	}

	private void move() {
		switch (direction) {
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		}
		rectangle.x = this.x;
		rectangle.y = this.y;
		if (x<0||y<0||x>TnakFrame.GAMEWIDTH||y>TnakFrame.GAMEHEIGHT) living = false;
	}

	public void collideWith(Tank tank) {
		if (this.group == tank.getGroup()) return;
		if (rectangle.intersects(tank.rectangle)) {
			tank.die();
			this.die();
			int eX = tank.getX()+tank.WIDTH/2-Explode.WIDTH/2;
			int eY = tank.getY()+tank.HEIGHT/2-Explode.HEIGHT/2;
			frame.explodes.add(new Explode(eX, eY, frame));
		}
	}

	public void die() {
		this.living = false;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
