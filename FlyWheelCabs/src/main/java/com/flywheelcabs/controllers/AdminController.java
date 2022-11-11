package com.flywheelcabs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flywheelcabs.exceptions.AdminException;
import com.flywheelcabs.modules.Admin;
import com.flywheelcabs.services.AdminServices;

@RestController
public class AdminController {


@Autowired
  private AdminServices aService;

  
   //save admin
  @PostMapping("/admin")
  public ResponseEntity<Admin> saveAdminHandler(@RequestBody Admin admin) throws AdminException{
	  Admin ad=aService.insertAdmin(admin);
	  return new ResponseEntity<Admin>(ad,HttpStatus.ACCEPTED);
	  
  }
  //Admin update
  @PutMapping("/update")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) throws AdminException{

	Admin ad1 = aService.updateAdmin( admin);
	return new ResponseEntity<Admin>(ad1,HttpStatus.OK);
	
	}
   
  //delete admin by adminId
  @DeleteMapping("/admin/{adminId}")
  public ResponseEntity<Admin> deleteEmployeeById(@PathVariable("adminId") Integer adminId)throws AdminException {
	
	Admin adm2=aService.deleteAdminById(adminId);
	return new ResponseEntity<Admin>(adm2,HttpStatus.OK);
}
  
  
  
}
