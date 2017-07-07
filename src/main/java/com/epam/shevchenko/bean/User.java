package com.epam.shevchenko.bean;

import java.io.Serializable;

import com.epam.shevchenko.enums.UserStatus;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String login;
	private String password;
	private String telephone;
	private UserStatus userStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((userStatus == null) ? 0 : userStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (userStatus != other.userStatus)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", telephone=" + telephone
				+ ", userStatus=" + userStatus + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long id, String login, String password, String telephone, UserStatus userStatus) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.telephone = telephone;
		this.userStatus = userStatus;
	}

	public User(String login, String password, String telephone) {
		super();
		this.login = login;
		this.password = password;
		this.telephone = telephone;
	}

	
	

}
