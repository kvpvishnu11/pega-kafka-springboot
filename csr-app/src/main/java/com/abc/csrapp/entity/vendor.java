package com.abc.csrapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class vendor {
	
	@Id
	public String VendorId;
	public String VendorName;
	public String VendorCity;
	public String getVendorId() {
		return VendorId;
	}
	public void setVendorId(String vendorId) {
		VendorId = vendorId;
	}
	public String getVendorName() {
		return VendorName;
	}
	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}
	public String getVendorCity() {
		return VendorCity;
	}
	public void setVendorCity(String vendorCity) {
		VendorCity = vendorCity;
	}

	
}
