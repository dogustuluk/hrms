package kodlamaio.hrms.core.utilities.adapters;

import java.time.LocalDate;

public interface ValidationService {
	boolean validateByMernis(long identificationNumber, String firstName, String lastName, LocalDate dateOfBirth );

}
