/*
 * Autor: Samuel Ivan Ramirez Navarro
 * Contacto: samuel.i.ramirez@gmail.com
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FaceChecker {
	
	private String path;
	private double res;
	private int threads;
	
	public FaceChecker() {
	}
	
	public double check() {
		File file = new File(path);
		BufferedImage image = null;
        try
        {
            image = ImageIO.read(file);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        int width = image.getWidth();
        int height = image.getHeight();
		int i;
		Thread[] pixelWorkers = new Thread[threads];
		long startTime = System.nanoTime();
		for(i = 0;i<threads;i++) {
			PixelWorker pw = new PixelWorker(this,image,i,width,height,threads);
			pixelWorkers[i] = new Thread(pw);
			pixelWorkers[i].start();
		}
		for(i = 0;i<threads;i++) {
			try {
				pixelWorkers[i].join();
			} catch (InterruptedException e) {
				System.out.println("Error");
				e.printStackTrace();
			}
		}
		Long endTime = System.nanoTime();
		endTime = endTime - startTime;
        System.out.println(String.format("time: %s", endTime.toString()));
		res = res/(width*height);
	    return res;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public double getRes() {
		return res;
	}

	public void setRes(double res) {
		this.res = res;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public synchronized void updateRes(double val) {
		res += val;
	}
	
	
}
