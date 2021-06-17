package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SystemStaffService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.SystemStaffDao;
import kodlamaio.hrms.entities.concretes.SystemStaff;
@Service
public class SystemStaffManager implements SystemStaffService{
	private SystemStaffDao systemStaffDao;
	
	@Autowired
	public SystemStaffManager(SystemStaffDao systemStaffDao) {
		super();
		this.systemStaffDao = systemStaffDao;
	}


	@Override
	public DataResult<List<SystemStaff>> getAll() {
		return new SuccessDataResult<List<SystemStaff>>(this.systemStaffDao.findAll());
	}

}
