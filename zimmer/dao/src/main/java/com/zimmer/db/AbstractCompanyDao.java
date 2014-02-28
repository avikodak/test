package com.zimmer.db;

import com.zimmer.dbbeans.Company;

public interface AbstractCompanyDao {
	public String createCompany(Company company) throws Exception;
	public Company getCompanyById(String companyId) throws Exception;
}
