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
		if(!checkIfNullInfoForEmployer(employer)) {
			return new ErrorResult("lütfen eksik bilgilerinizi tamamlayınız");
		}
		
		if(!checkIfEqualEmailAndDomain(employer.getEmail(), employer.getWebSite())){
			return new ErrorResult("Geçersiz e-posta adresi");
		} //kontrol et
		
		if(!checkIfEmailExist(employer.getEmail())) {
			return new ErrorResult("girilen email adresi mevcut, lütfen başka bir email adresi ile kayıt olun");
		}
		
		if(!checkIfEqualPasswordAndConfirmPassword(employer.getPassword(), confirmPassword)) {
			return new ErrorResult("Şifreler farklı, lütfen şifreleri kontrol edin");
		}
		
		
		
		employerService.add(employer);
		String code = verificationService.sendCode();
		generatedCode(code, employer.getId(), employer.getEmail());
		return new SuccessResult("kayıt işlemi başarıyla tamamlandı");
	}

	
	@Override
	public Result registerCandidate(Candidate candidate, String confirmPassword) {
	/*
		if(!checkIfRealPerson(candidate.getIdentificationNumber(), candidate.getFirstName(), candidate.getLastName(), candidate.getDateOfBirth().getYear()) == true) {
			return new ErrorResult("T.C. kimlik numarası doğrulanamadı");
		}*/
		
		if(!checkIfNullInfoForCandidate(candidate, confirmPassword)) {
			return new ErrorResult("Eksik bilgilerinizi tamamlayınız");
		}
		
		
		if(!checkIfExistsNationalId(candidate.getIdentificationNumber())) {
			return new ErrorResult("bu kimlik numarasına ait kişi kayıtlıdır");
		}
		
		
		if(!checkIfEmailExist(candidate.getEmail())) {
			return new ErrorResult("e-posta kayıtlı");
		}
		
				
		candidateService.add(candidate);
		String code = verificationService.sendCode();
		generatedCode(code, candidate.getId(), candidate.getEmail());
		return new SuccessResult("Kayıt başarılı");
	}
	
	
	public boolean checkIfNullInfoForEmployer(Employer employer) {
		if(employer.getCompanyName() !=null && employer.getWebSite() !=null && employer.getEmail() != null && employer.getPhoneNumber() != null && employer.getPassword() != null) {
			return true;
		}
		return false;
	}
	
	public boolean checkIfEqualEmailAndDomain(String email, String website) {
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
	
	private boolean checkIfEqualPasswordAndConfirmPassword(String password, String confirmPassword) {
		if(!password.equals(confirmPassword)) {
			return false;
		}
		return true;
	}
	
	private boolean checkIfRealPerson(long identificationNumber, String firstName, String lastName, LocalDate dateOfBirth) {
		if(validationService.validateByMernis(identificationNumber, firstName, lastName, dateOfBirth)) {
			return true;
		}
		return false;
	}
	
	private boolean checkIfNullInfoForCandidate(Candidate candidate, String confirmPassword) {
		if(candidate.getDateOfBirth() != null && candidate.getEmail() != null && candidate.getFirstName() != null && candidate.getIdentificationNumber() != null
				&& candidate.getLastName() != null && candidate.getPassword() != null) {
			return true;
		}
		return false;
	}
	
	private boolean checkIfExistsNationalId(String identificationNumber) {
		if(this.candidateService.getCandidateByIdentificationNumber(identificationNumber).getData() == null) {
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
