package com.zimmer.dbbeans;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_email_address_map")
public class UserEmailAddress implements Serializable{

	private static final long serialVersionUID = 1L;
	private String sno;
	private UserDetailsCommon commonDetails;
	private String emailAddress;

	public UserEmailAddress() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sno")
	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="type",column=@Column(name="email_type")),
		@AttributeOverride(name="isActive",column=@Column(name="is_email_active"))
	})
	public UserDetailsCommon getCommonDetails() {
		return commonDetails;
	}

	public void setCommonDetails(UserDetailsCommon commonDetails) {
		this.commonDetails = commonDetails;
	}

	@Basic(optional=false)
	@Column(name="user_email_address",nullable=false)
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
