package spm.spring.rest.bean;

import java.util.Date;

import spm.spring.rest.util.JsonDateDeserializer;
import spm.spring.rest.util.JsonDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ContactBean {
	private int id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private Date dob;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDob() {
		return dob;
	}
	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "ContactBean [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phone=" + phone + ", email="
				+ email + ", dob=" + dob + "]";
	}
	
}
