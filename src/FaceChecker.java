import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class FaceChecker {
	
	private String path;
	private double res;
	private int threads;
	
	public FaceChecker() {
	}
	
	public double check() {
		Mat image;
		image = Imgcodecs.imread(path);
		image.convertTo(image, CvType.CV_64FC3);
		int size = (int) (image.total() * image.channels()); 
		double[] temp = new double[size];
		image.get(0, 0, temp);
		int i;
		Thread[] pixelWorkers = new Thread[threads];
		for(i = 0;i<threads;i++) {
			PixelWorker pw = new PixelWorker(this,i,temp,size,threads);
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
		res = res/size;
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
