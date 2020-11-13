
public class Face {
	private Integer id;
	private Double value;
	private String name;
	private String desc;
	
	public Face(int id, double value, String name, String desc) {
		this.id = id;
		this.value = value;
		this.name = name;
		this.desc = desc;
	}

	public String print() {
		return id.toString()+","+value.toString()+","+name+","+desc;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
