package test;

import java.io.Serializable;

public class NumUnit implements Serializable {
	
	public int num;
	
	public NumUnit() {
		
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return String.format("NumUnit [num=%s]", num);
	}
	
	
}
