package yo.benqcz.exam.domain;

public class Student {

	private String sid;
	private String name;
	private String location;
	private String eid;
	private String grade;

	public Student() {
	}

	public Student(String sid, String name, String location, String eid,
			String grade) {
		super();
		this.sid = sid;
		this.name = name;
		this.location = location;
		this.eid = eid;
		this.grade = grade;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
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
				"Student [eid=%s, grade=%s, location=%s, name=%s, sid=%s]",
				eid, grade, location, name, sid);
	}

}
