package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;

public interface CandidateService {
	Result add(Candidate candidate);
	DataResult<Candidate> getById(int id);
	DataResult<List<Candidate>> getAll();
	DataResult<Candidate> getCandidateByIdentificationNumber(String identificationNumber);

}
