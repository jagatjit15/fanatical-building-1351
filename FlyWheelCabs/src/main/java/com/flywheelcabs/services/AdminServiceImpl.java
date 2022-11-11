package com.flywheelcabs.services;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flywheelcabs.exceptions.AdminException;
import com.flywheelcabs.exceptions.CustomerException;
import com.flywheelcabs.exceptions.LoginException;
import com.flywheelcabs.modules.Admin;
import com.flywheelcabs.modules.Customer;
import com.flywheelcabs.modules.LoginSession;
import com.flywheelcabs.modules.TripDetails;
import com.flywheelcabs.repositories.AdminRepo;
import com.flywheelcabs.repositories.CustomerRepo;
import com.flywheelcabs.repositories.LoginSessionDao;
import com.flywheelcabs.repositories.TripdataRepository;
@Service
public class AdminServiceImpl implements AdminServices {
	
  @Autowired
	private AdminRepo aRepo;
  
    @Autowired
    private CustomerRepo cRepo;
    

    @Autowired
    private TripdataRepository TktRepo;


	@Autowired
	private LoginSessionDao loginDao;
    
	
	@Override
	public Admin insertAdmin(Admin admin) throws AdminException {
		// TODO Auto-generated method stub
		Admin ad=aRepo.save(admin);
		return ad;
	}

	@Override
	public Admin updateAdmin(Admin admin)throws AdminException, LoginException {
		
		Optional<LoginSession> existingSession = loginDao.findById(admin.getAdminId());
		
		if(existingSession == null) throw new LoginException("Please login to update your data");
		
		Optional<Admin> optional = aRepo.findById(admin.getAdminId());
		
		if(optional.isPresent()) {
			return aRepo.save(admin);
		}
		else {
			throw new AdminException("Invalid Admin details");
		}
	}
	
	@Override
	public Admin deleteAdminById(Integer adminId) throws AdminException, LoginException {
		// TODO Auto-generated method stub
		
		Optional<LoginSession> existingSession = loginDao.findById(adminId);
		
		if(existingSession == null) throw new LoginException("Please login to update your data");
		
		Optional<Admin> opt=aRepo.findById(adminId);
		
		if(opt.isPresent()) {
			
			LoginSession session=existingSession.get();
			loginDao.delete(session);
			
			
			Admin existingAdmin=opt.get();
			aRepo.delete(existingAdmin);
			
		  return existingAdmin;	
		  
		}else {
			
			throw new AdminException("Admin does not exist");
		}
	}

	@Override

	public List<TripDetails> getAllTrips(Integer customerId) throws AdminException, LoginException, CustomerException {

		Optional<LoginSession> existingSession = loginDao.findById(customerId);
		
		if(existingSession == null) throw new LoginException("Please login to update your data");
		 
		Optional<Customer> optional  =cRepo.findById(customerId);
		
	  if(optional.isPresent()) {
		  List<TripDetails> triplist=optional.get().getTriplist();
		   
		  if(triplist.isEmpty()) {
			  throw new CustomerException("No Trips Booked");
		  }
		  return triplist;
	  }
	  throw new CustomerException("No customer available");
	  
	}

	@Override
	public List<TripDetails> getTripCabwise() throws AdminException {

		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripDetails> getTripCustomerwise(Integer customerId) throws AdminException {
		
//		Optional<LoginSession> existingSession = loginDao.findById(admin.getAdminId());
//		
//		if(existingSession == null) throw new LoginException("Please login to update your data");
	
//		List<TripDetails> listOfTrips =cRepo.findByCustomerId(customerId);
//		if (listOfTrips.isEmpty())
//			throw new CustomerException("No trips Found by this Customer id " + customerId);
    	//return listOfTrips;
		return null;
		  
	}

	@Override
	public List<TripDetails> getTripDatewise() throws AdminException {

//		Optional<LoginSession> existingSession = loginDao.findById(admin.getAdminId());
//		
//		if(existingSession == null) throw new LoginException("Please login to update your data");
		
		//List<TripDetails> getTripDatewise =cRepo.getTripDatewise();
		//if (getTripDatewise.isEmpty())
//			throw new CustomerException("No trips Found");
    	//return getTripDatewise;
		return null;
	}

	@Override
	public List<TripDetails> getAllTripsForDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate)
			throws AdminException {

//		Optional<LoginSession> existingSession = loginDao.findById(admin.getAdminId());
//		
//		if(existingSession == null) throw new LoginException("Please login to update your data");
		return null;
	}

 
	
}
