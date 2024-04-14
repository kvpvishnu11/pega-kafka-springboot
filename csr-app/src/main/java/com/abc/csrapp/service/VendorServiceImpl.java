package com.abc.csrapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import com.abc.csrapp.entity.vendor;
import com.abc.csrapp.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired 
    private VendorRepository vendorRepository;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate; // Use StringSerializer for value

    @Override
    public void saveTheVendor(vendor v) {
        vendorRepository.save(v);
    }

    @Override
    public void updateTheVendor(vendor v) {
    	vendor v1 = new vendor();
    	v1.setVendorCity(v.getVendorCity());
    	v1.setVendorId(v.getVendorId());
    	v1.setVendorName(v.getVendorName());
    	vendorRepository.save(v1);
    	
        try {
            // Convert vendor object to JSON string
            String jsonVendor = "{\"VendorId\":\"" + v.getVendorId() + "\",\"VendorName\":\"" + v.getVendorName() + "\",\"VendorCity\":\"" + v.getVendorCity() + "\"}";

            // Send JSON string to Kafka
            kafkaTemplate.send("JavaToPega", jsonVendor);
        } catch (Exception e) {
            // Handle serialization exception
            e.printStackTrace();
        }
    }
    
    
    // Kafka Listener - Consumer
    
    @KafkaListener(topics = "PegaToJava", groupId = "pega-spring-group")
    public void listenToTopic(String jsonMessage) { // Change the parameter type to String
        try {
            // Parse JSON string manually
            JSONObject jsonObject = new JSONObject(jsonMessage);
            String vendorName = jsonObject.getString("VendorName");
            String vendorId = jsonObject.getString("VendorId");
            String vendorCity = jsonObject.getString("VendorCity");

            vendor v2= new vendor();
            v2.setVendorCity(vendorCity);
            v2.setVendorId(vendorId);
            v2.setVendorName(vendorName);
            vendorRepository.save(v2);
            
            
           
        } catch (JSONException e) {
            // Handle parsing exception
            e.printStackTrace();
        }
    }

	@Override
	public List<vendor> getAllVendors() {
		
		List<vendor> v3 = vendorRepository.findAll();
		return v3;
	}
}
