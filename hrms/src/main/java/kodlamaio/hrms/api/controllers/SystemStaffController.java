package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SystemStaffService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.SystemStaff;

@RestController
@RequestMapping("/api/systemstaff")
public class SystemStaffController {
	private SystemStaffService systemStaffService;
	
	@Autowired
	public SystemStaffController(SystemStaffService systemStaffService) {
		super();
		this.systemStaffService = systemStaffService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<SystemStaff>> getAll(){
		return this.systemStaffService.getAll();
	}
	
	

}
