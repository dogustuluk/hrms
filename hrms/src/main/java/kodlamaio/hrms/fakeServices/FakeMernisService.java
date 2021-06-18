package kodlamaio.hrms.fakeServices;

import java.time.LocalDate;

public class FakeMernisService {
	public boolean validateByHuman(long identificationNumber, String firstName, String lastName, LocalDate dateOfBirth ){
		
		System.out.println(firstName +" " + lastName + "adlı kişi onaylanmıştır");
		return true;

}
}
