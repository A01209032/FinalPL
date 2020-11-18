/*
 * Autor: Samuel Ivan Ramirez Navarro
 * Contacto: samuel.i.ramirez@gmail.com
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PixelWorker implements Runnable {
	
	private FaceChecker master;
	private Integer id;
	private BufferedImage image;
	private Integer width;
	private Integer height;
	private Integer threads;
	
	public PixelWorker(FaceChecker master,BufferedImage image, int id,int width, int height, int threads) {
		this.master = master;
		this.image = image;
		this.id = id;
		this.width = width;
		this.height = height;
		this.threads = threads;
	}
	
	@Override
	public void run() {
		double res=0;
		double temp;
		int index = id;
		while(index<height) {
			for (int col = 0; col < width; col++) {
				Color mycolor = new Color(image.getRGB(col, index));
				temp=0;
				temp+=mycolor.getBlue();
				temp+=mycolor.getGreen();
				temp+=mycolor.getRed();
				temp = temp/3;
	            res += temp;
	         }
			index += threads;
		}     
		master.updateRes(res);
	}

}
