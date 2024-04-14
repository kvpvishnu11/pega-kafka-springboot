package com.abc.csrapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.csrapp.entity.vendor;
import com.abc.csrapp.repository.VendorRepository;

public interface VendorService {
	
	
	
	public void saveTheVendor(vendor v) ;
	
	public void updateTheVendor(vendor v);
	
	public List<vendor> getAllVendors();
	}


