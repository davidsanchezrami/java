package hazelcast.model;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String phone;
	private String surname;
	private String accion;
	private String foco;
	
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", phone=" + phone + ", surname=" + surname + "]";
	}

	public User(){
		
	}
	
	public User(String name, String email, String phone, String surname) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFoco() {
		return foco;
	}

	public void setFoco(String foco) {
		this.foco = foco;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	

}
