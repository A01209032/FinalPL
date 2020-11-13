public class PixelWorker implements Runnable {
	
	private FaceChecker master;
	private Integer id;
	private double[] temp;
	private Integer size;
	private Integer threads;
	
	public PixelWorker(FaceChecker master, int id, double[] temp, int size, int threads) {
		this.master = master;
		this.id = id;
		this.temp = temp;
		this.size = size;
		this.threads = threads;
	}
	
	@Override
	public void run() {
		double res=0;
		int index = id;
		while(index<size) {
			res += temp[index]/3;
			index += threads;
		}
		master.updateRes(res);
	}

}
