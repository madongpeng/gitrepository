package tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {
	
	private int x,y;
	//主坦克默认方向为上
	Direction direction = Direction.UP;
	//坦克速度
	private static final int SPEED = PropertyManger.getInt("tankSpeed");
	//坦克是否移动
	private boolean moving = true;
	
	private TnakFrame frame = null;
	
	private boolean living = true;
	
	private Random random = new Random();
	//火力区别
	private Group group = Group.BAD;
	
	Rectangle rectangle = new Rectangle();
	
	public static int WIDTH = ResourceManager.goodTankU1.getWidth();
	public static int HEIGHT = ResourceManager.goodTankU1.getHeight();
	
	public Tank(int x, int y, Direction direction,Group group,TnakFrame frame) {
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
		if (!living) frame.tanks.remove(this);
		if (random.nextInt(10)>5) {
			switch (direction) {
			case UP:
				g.drawImage(this.group==Group.GOOD?ResourceManager.goodTankU1:ResourceManager.badTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group==Group.GOOD?ResourceManager.goodTankD1:ResourceManager.badTankD, x, y, null);
				break;
			case LEFT:
				g.drawImage(this.group==Group.GOOD?ResourceManager.goodTtankL1:ResourceManager.badTtankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group==Group.GOOD?ResourceManager.goodTankR1:ResourceManager.badTankR, x, y, null);
				break;	
			}
		}else {
			switch (direction) {
			case UP:
				g.drawImage(this.group==Group.GOOD?ResourceManager.goodTankU2:ResourceManager.badTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group==Group.GOOD?ResourceManager.goodTankD2:ResourceManager.badTankD, x, y, null);
				break;
			case LEFT:
				g.drawImage(this.group==Group.GOOD?ResourceManager.goodTtankL2:ResourceManager.badTtankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group==Group.GOOD?ResourceManager.goodTankR2:ResourceManager.badTankR, x, y, null);
				break;	
			}
		}
			
		
		move();
	}

	private void move() {
		if(!moving)return;
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
		
		if (this.group==Group.BAD && random.nextInt(100)>95)	fire();
		if (this.group==Group.BAD && random.nextInt(10)>8) randomDirection();
		
		boundsCheck();
		rectangle.x = this.x;
		rectangle.y = this.y;
	}

	private void boundsCheck() {
		if (this.x<0)this.x = 0;
		if(this.y<30)this.y = 30;
		if(this.x>TnakFrame.GAMEWIDTH-WIDTH)this.x=TnakFrame.GAMEWIDTH-WIDTH;
		if(this.y>TnakFrame.GAMEHEIGHT-HEIGHT)this.y=TnakFrame.GAMEHEIGHT-HEIGHT;	
	}

	private void randomDirection() {
		direction = direction.values()[random.nextInt(4)];
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void fire() {
		int bx = x+WIDTH/2-Bullet.WIDTH/2;
		int by = y+HEIGHT/2-Bullet.HEIGHT/2;
		frame.bullets.add(new Bullet(bx, by, direction,this.group,frame));
		if (this.group == Group.GOOD) {
			new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
		}
	}
	
	public void die(){
		this.living = false;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
