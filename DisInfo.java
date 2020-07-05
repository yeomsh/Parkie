package JavaProject;

public class DisInfo {
	private String name;
	private int rate;
	public DisInfo() {
		
	}
	public DisInfo(String name, int rate) {
		this.name=name;
		this.rate=rate;
	}
	public String toString() {
		return "할인 이름: "+name+","+"할인율: "+rate;
	}
}
