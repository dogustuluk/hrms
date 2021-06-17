
package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name ="user_id")
public class Employer extends User{
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "web_site")
	private String webSite;
	@Column(name = "phone_number")
	private String phoneNumber;

}
