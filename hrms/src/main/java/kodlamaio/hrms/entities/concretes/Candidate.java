
package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="candidates")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name ="user_id")


public class Candidate extends User {

	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="identification_number")
	private String identificationNumber;
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	

}

