package tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceManager {
	public static BufferedImage goodTankU1,goodTankD1,goodTtankL1,goodTankR1,goodTankU2,goodTankD2,goodTtankL2,goodTankR2;
	public static BufferedImage badTankU,badTankD,badTtankL,badTankR;
	public static BufferedImage bulletU,bulletD,bulletL,bulletR;
	public static BufferedImage[] explodes = new BufferedImage[16];
	
	static{
		try {
			goodTankU1 = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankD1 = ImageUtil.rotateImage(goodTankU1, 180);
			goodTtankL1 = ImageUtil.rotateImage(goodTankU1, -90);
			goodTankR1 = ImageUtil.rotateImage(goodTankU1, 90);
			
			goodTankU2 = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
			goodTankD2 = ImageUtil.rotateImage(goodTankU2, 180);
			goodTtankL2 = ImageUtil.rotateImage(goodTankU2, -90);
			goodTankR2 = ImageUtil.rotateImage(goodTankU2, 90);
			
			badTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			badTtankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			
			bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			
			for (int i = 0; i < explodes.length; i++) {
				explodes[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
