package kodlamaio.hrms.core.verification;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class VerificationManager implements VerificationService {

	@Override
	public void sendLink(String email) {
		UUID uuid = UUID.randomUUID();
		String verificationLink = "https://hrmsverificationmail/" + uuid.toString();
		System.out.println("doğrulama adresi: " +email);
		System.out.println("hesabınızı doğrulamak için bağlantıya tıklayın: " + verificationLink);
		
	}

	@Override
	public String sendCode() {
		UUID uuid = UUID.randomUUID();
		String verificationCode = uuid.toString();
		System.out.println("aktivasyon kodunuz: "+ verificationCode);
		return verificationCode;
	}

}
