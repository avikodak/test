package com.zimmer.dbbeans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="company_log")
public class CompanyLog implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer logId;
	private Company logOwningCompany;
	private LogCommonDetails companyLogCommon;
	private User parentUser;
	
	public CompanyLog() {
		super();
	}

	public CompanyLog(Company logOwningCompany,
			LogCommonDetails companyLogCommon) {
		super();
		this.logOwningCompany = logOwningCompany;
		this.companyLogCommon = companyLogCommon;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "log_id", nullable = false)
	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_company_id")
	public Company getLogOwningCompany() {
		return logOwningCompany;
	}

	public void setLogOwningCompany(Company logOwningCompany) {
		this.logOwningCompany = logOwningCompany;
	}

	@Embedded
	public LogCommonDetails getCompanyLogCommon() {
		return companyLogCommon;
	}

	public void setCompanyLogCommon(LogCommonDetails companyLogCommon) {
		this.companyLogCommon = companyLogCommon;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="logging_owner_id")
	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

}
