package com.benqcz.school.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Teacher implements Serializable {
	
	private Integer id;
	private String name;
	private List<Student> students = new LinkedList<Student>();

	public Teacher() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("id: %d name: %s students: [", id, name));
		for (Student stud : students) {
			sb.append(stud);
		}
		sb.append("]");
		return sb.toString();
	}

}
