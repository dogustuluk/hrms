package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.JobDao;
import kodlamaio.hrms.entities.concretes.jobs;

@Service
public class JobManager implements JobService {
	
	private JobDao jobDao;
	
	@Autowired
	public JobManager(JobDao jobDao) {
		super();
		this.jobDao = jobDao;
	}
	
	@Override
	public DataResult<List<jobs>> getAll() {
		return new SuccessDataResult<List<jobs>>(this.jobDao.findAll(), "sistemdeki iş pozisyonlarının tümü listelendi");
		
		
		
	}

	

}