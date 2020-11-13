import java.util.Vector;

public class FaceDataBase {
	private Vector<Face> faces;
	private Integer size;
	private FaceChecker fc;
	private Integer threads;
	private static FaceDataBase single_instance = null;
	
	private FaceDataBase() {
		threads = 4;
		faces = new Vector<Face>();
		size = 0;
		fc = new FaceChecker();
	}
	
	public static FaceDataBase getInstance() { 
        if (single_instance == null) 
            single_instance = new FaceDataBase(); 
  
        return single_instance; 
    }
	
	public synchronized String addFace(String path, String name, String desc) {
		double value;
		fc.setPath(path);
		fc.setThreads(threads);
		value = fc.check();
		Face face = new Face(size,value,name,desc);
		size ++;
		faces.add(face);
		return "Success:"+face.print();
	}
	
	public synchronized String checkFace(String path) {
		double value;
		Face res;
		fc.setPath(path);
		fc.setThreads(threads);
		value = fc.check();
		if(size==0) {
			System.out.println("Todavia no hay caras guardadas");
			return new Face(0,0.0,"default","default").print()+","+value;
		}
		res = findClosest(value);
		return res.print()+","+value;
	}
	
	public synchronized Integer getSize() {
		return size;
	}
	
	public synchronized String getAll() {
		String res = "";
		for(int i=0;i<size;i++) {
			System.out.println(size.toString());
			System.out.println(faces.get(i).print());
			res += faces.get(i).print();
			res += "\n";
		}
		return res;
	}
	
    public Face findClosest(Double target) { 
        Integer n = size;
        Face aux = new Face(0,0.0,"default","default");
        for(int i=0;i<n;i++) {
        	if(Math.abs(faces.get(i).getValue()-target) < Math.abs(aux.getValue() - target)) {
        		aux = faces.get(i);
        	}
        }
        return aux;
        
    } 
  
    
	public synchronized String getThreads() {
		return threads.toString();
	}

	public synchronized String setThreads(int threads) {
		this.threads = threads;
		return "Success";
	} 
	
	public synchronized String delete(int id) {
		for(int i=0; i<size;i++) {
			if(faces.get(i).getId()==id) {
				faces.remove(i);
				size--;
				return "Success";
			}
		}
		
		return "Error";
	}
	
}
