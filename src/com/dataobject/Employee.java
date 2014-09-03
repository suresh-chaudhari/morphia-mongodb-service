package com.dataobject;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * 
 * @author suresh
 *
 */
@Entity(value="employee", noClassnameStored=true)
public class Employee {

	@Id
	private ObjectId id;
	private String name;
	private String designation;
	private int empId;

	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation="
				+ designation + ", empId=" + empId + "]";
	}
	
}
