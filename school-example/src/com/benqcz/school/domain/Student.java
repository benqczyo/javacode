package com.benqcz.school.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Student implements Serializable {

	private Integer id;
	private String name;
	private List<Teacher> teachers = new LinkedList<Teacher>();

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

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return String.format("Student [id=%s, name=%s]", id, name);
	}

}
