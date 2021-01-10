package tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TnakFrame extends Frame{

	public static final int GAMEWIDTH = PropertyManger.getInt("GAMEWIDTH"),GAMEHEIGHT = PropertyManger.getInt("GAMEHEIGHT");
	Tank myTank = new Tank(300, 500, Direction.UP,Group.GOOD, this);
	//子弹和敌方坦克集合
	List<Bullet> bullets = new ArrayList<>();
	List<Tank>tanks = new ArrayList<>();
	List<Explode> explodes = new ArrayList<>();
	
	public TnakFrame(){
		//setSize(700,600);
		//获取屏幕的宽
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		//获取屏幕的高：
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		//设置窗体的位置：
		setBounds((width-700)/2, (height-600)/2, GAMEWIDTH, GAMEHEIGHT);
		//设置窗体不可以改变大小
		setResizable(false);
		//添加标题
		setTitle("坦克大战");
		//键盘监听器
		this.addKeyListener(new MyKeyListener());
		//关闭窗口，程序停止
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//设置窗体可见
		setVisible(true);
	}
	//解决闪烁问题，利用双缓冲
	Image ScreenImage = null;
	@Override
	public void update(Graphics g) {
		if (ScreenImage == null) {
			ScreenImage = this.createImage(GAMEWIDTH, GAMEHEIGHT);
		}
		Graphics graphics = ScreenImage.getGraphics();
		Color color = graphics.getColor();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, GAMEWIDTH, GAMEHEIGHT);
		graphics.setColor(color);
		paint(graphics);
		g.drawImage(ScreenImage, 0, 0, null);
	}
	
	@Override
	public void paint(Graphics g) {
		
		Color color = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹数量："+bullets.size(), 10,50);
		g.drawString("敌方坦克数量："+tanks.size(), 10,70);
		g.drawString("爆炸数量："+explodes.size(), 10,90);
		g.setColor(color);
		myTank.paint(g);
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		
		//判断子弹和坦克是否相撞
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
		
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		
	}
	
	//定义内部类，键盘监听类
	class MyKeyListener extends KeyAdapter{
		//根据布尔值的组合判断坦克的方向
		boolean bU = false;
		boolean bD = false;
		boolean bL = false;
		boolean bR = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				bU=true;
				break;
			case KeyEvent.VK_DOWN:
				bD=true;			
				break;
			case KeyEvent.VK_LEFT:
				bL=true;
				break;
			case KeyEvent.VK_RIGHT:
				bR=true;
				break;
			default:
				break;
			}
			//设置主坦克方向
			setMainTankDirection();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				bU=false;
				break;
			case KeyEvent.VK_DOWN:
				bD=false;			
				break;
			case KeyEvent.VK_LEFT:
				bL=false;
				break;
			case KeyEvent.VK_RIGHT:
				bR=false;
				break;
			case KeyEvent.VK_CONTROL:
				myTank.fire();
				break;
			default:
				break;
			}
			
			setMainTankDirection();
		}

		private void setMainTankDirection() {
			if (!bU && !bD && !bL && !bR) {
				myTank.setMoving(false);
			}else {
				myTank.setMoving(true);
				if (bU) myTank.setDirection(Direction.UP); 
				if (bD) myTank.setDirection(Direction.DOWN);
				if (bL) myTank.setDirection(Direction.LEFT);
				if (bR) myTank.setDirection(Direction.RIGHT);
			}
			
		}
		
	}
	
}
