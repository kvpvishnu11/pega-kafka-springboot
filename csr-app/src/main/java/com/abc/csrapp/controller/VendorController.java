package com.abc.csrapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.csrapp.entity.vendor;
import com.abc.csrapp.service.VendorService;

@RestController
@RequestMapping("/vendor-api")
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@PostMapping("/save")
	public void saveTheVendor(@RequestBody vendor v) {
		
		vendorService.saveTheVendor(v);
		
		
	}
	
	@PutMapping("/update")
	public void updateVendorByCSR(@RequestBody vendor v) {
		vendorService.updateTheVendor(v);
	}
	
	@GetMapping("/list")
	public List<vendor> getAllVendors() {
		
		return vendorService.getAllVendors();
	}
	

}
