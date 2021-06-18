package kodlamaio.hrms.core.utilities.adapters;

import java.time.LocalDate;

import kodlamaio.hrms.fakeServices.FakeMernisService;

public class MernisServiceAdapter implements ValidationService{

	@Override
	public boolean validateByMernis(long identificationNumber, String firstName, String lastName,
			LocalDate dateOfBirth) {
		FakeMernisService fakeMernisService = new FakeMernisService();
		boolean result = true;
		
		try {
			result = fakeMernisService.validateByHuman(identificationNumber, firstName, lastName, dateOfBirth);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
