package com.zimmer.dbbeans;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_website_map")
public class UserWebsite implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sno;
	private UserDetailsCommon commonDetails;
	private String websiteUrl;

	public UserWebsite() {
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
	
	
	public UserWebsite(UserDetailsCommon commonDetails, String websiteUrl) {
		super();
		this.commonDetails = commonDetails;
		this.websiteUrl = websiteUrl;
	}

	@Column(name="user_website_url")
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="type",column=@Column(name="website_type")),
		@AttributeOverride(name="isActive",column=@Column(name="is_website_active"))
	})
	public UserDetailsCommon getCommonDetails() {
		return commonDetails;
	}

	public void setCommonDetails(UserDetailsCommon commonDetails) {
		this.commonDetails = commonDetails;
	}

}
