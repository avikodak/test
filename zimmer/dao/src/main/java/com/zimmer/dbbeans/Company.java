package com.zimmer.dbbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zimmer.utils.UUIDGenerators;

@Entity
@Table(name="company_details")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;
	private String companyId;
	private String name;
	private String registeredBy;
	private Address companyAddress;
	private Set<User> companyUsers;
	private Integer usersCount;
	private Date createdAt;
	private Date updatedAt;
	private Boolean isActive;
	private Set<CompanyLog> companyLogs = new HashSet<CompanyLog>();
	
	public Company() {
		this.companyId = UUIDGenerators.generateCompanyId();
		this.createdAt = new Date();
		this.usersCount = 0;
		this.isActive = true;
	}

	public Company(String name, String registeredBy, Address companyAddress) {
		this.companyId = UUIDGenerators.generateCompanyId();
		this.name = name;
		this.registeredBy = registeredBy;
		this.companyAddress = companyAddress;
		this.createdAt = new Date();
		this.usersCount = 0;
		this.isActive = true;
	}

	public Company(String name, String registeredBy,
			Integer usersCount, Date createdAt, Date updatedAt, Boolean isActive) {
		super();
		this.name = name;
		this.registeredBy = registeredBy;
		this.usersCount = usersCount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isActive = isActive;
	}

	@Id
	@Column(name="company_id")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Basic(optional=false)
	@Column(name="company_name",nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic(optional=false)
	@Column(name="company_registered_by",nullable=false)
	public String getRegisteredBy() {
		return registeredBy;
	}

	public void setRegisteredBy(String registeredBy) {
		this.registeredBy = registeredBy;
	}

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="addressLine1",column=@Column(name="company_address_line1")),
		@AttributeOverride(name="addressLine2",column=@Column(name="company_address_line2")),
		@AttributeOverride(name="city",column=@Column(name="company_city")),
		@AttributeOverride(name="state",column=@Column(name="company_state")),
		@AttributeOverride(name="country",column=@Column(name="company_country")),
		@AttributeOverride(name="zipcode",column=@Column(name="company_zipcode"))
	})
	public Address getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(Address companyAddress) {
		this.companyAddress = companyAddress;
	}

	@OneToMany(cascade={CascadeType.ALL},mappedBy="userCompany",fetch=FetchType.LAZY)
	public Set<User> getCompanyUsers() {
		return companyUsers;
	}

	public void setCompanyUsers(Set<User> companyUsers) {
		this.companyUsers = companyUsers;
	}

	public void addUserToCompany(User user){
		this.companyUsers.add(user);
		user.setUserCompany(this);
	}
	
	@Basic(optional=false)
	@Column(name="company_users_count",nullable=false)
	public Integer getUsersCount() {
		return usersCount;
	}

	public void setUsersCount(Integer usersCount) {
		this.usersCount = usersCount;
	}

	@Basic(optional=false)
	@Column(name="company_created_at",nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Basic(optional=false)
	@Column(name="company_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Basic(optional=false)
	@Column(name="is_company_active",nullable=false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="logOwningCompany")
	public Set<CompanyLog> getCompanyLogs() {
		return companyLogs;
	}

	public void setCompanyLogs(Set<CompanyLog> companyLogs) {
		this.companyLogs = companyLogs;
	}

	public void addLogToCompanyLogs(CompanyLog companyLog){
		companyLogs.add(companyLog);
		companyLog.setLogOwningCompany(this);
	}
	
}
