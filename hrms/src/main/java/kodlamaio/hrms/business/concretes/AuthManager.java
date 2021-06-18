package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.adapters.ValidationService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.verification.VerificationService;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.VerificationCode;

@Service
public class AuthManager implements AuthService{
	private CandidateService candidateService;
	private EmployerService employerService;
	private UserService userService;
	private VerificationCodeService verificationCodeService;
	private ValidationService validationService;
	private VerificationService verificationService;
	
	@Autowired
	public AuthManager(CandidateService candidateService, EmployerService employerService, UserService userService,
			VerificationCodeService verificationCodeService, ValidationService validationService,
			VerificationService verificationService) {
		super();
		this.candidateService = candidateService;
		this.employerService = employerService;
		this.userService = userService;
		this.verificationCodeService = verificationCodeService;
		this.validationService = validationService;
		this.verificationService = verificationService;
	}

	
	
	@Override
	public Result registerEmployer(Employer employer, String confirmPassword) {
		if (!checkIfEqualEmailAddressDomain(confirmPassword, confirmPassword)) {
			return new ErrorResult("geçersiz email adresi, email bilginizi gözden geçiriniz");
		}
		
		if(!checkIfEmailExist(employer.getEmail())) {
			return new ErrorResult("girilen email adresi mevcut, lütfen başka bir email adresi ile kayıt olun");
		}
		
		
		
		employerService.add(employer);
		String code = verificationService.sendCode();
		generatedCode(code, employer.getId(), employer.getEmail());
		return new SuccessResult("kayıt işlemi başarıyla tamamlandı");
	}

	
	@Override
	public Result registerCandidate(Candidate candidate, String confirmPassword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public boolean checkIfEqualEmailAddressDomain(String email, String website) {
		String[] emailArr = email.split("@", 2);
		String domain = website.substring(4, website.length());
		
		if(emailArr[1].equals(domain)) {
			return true;
		}
		return false;
		
	}
	
	private boolean checkIfEmailExist(String email) {
		if(this.userService.getUserByEmail(email).getData()==null) {
			return true;
		}
		return false;
	}
	
	private boolean checkIfRealPerson(long identificationNumber, String firstName, String lastName, LocalDate dateOfBirth) {
		if(validationService.validateByMernis(identificationNumber, firstName, lastName, dateOfBirth)) {
			return true;
		}
		return false;
	}
	
	public void generatedCode(String code, int id, String email) {
		VerificationCode verificationCode = new VerificationCode(id,id,code,false,LocalDate.now());
		this.verificationCodeService.add(verificationCode);
		System.out.println("doğrulama kodu mail adresinize yollandı.");
	}

}
