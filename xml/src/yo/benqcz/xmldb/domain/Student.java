package yo.benqcz.xmldb.domain;

public class Student {
	
	private String sid;
	private String eid;
	private String name;
	private String location;
	private String grade;
	
	public Student() {}

	public Student(String sid, String eid, String name, String location, String grade) {
		this.sid = eid;
		this.eid = sid;
		this.name = name;
		this.location = location;
		this.grade = grade;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return String.format(
				"%s\t%s\t%s\t%s\t%s",
				sid, name, location, eid, grade);
	}

	
	
}
