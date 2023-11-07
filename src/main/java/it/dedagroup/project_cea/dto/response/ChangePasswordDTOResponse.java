package it.dedagroup.project_cea.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordDTOResponse {
	
	@NotBlank(message = "Il campo 'username' non è valido")
	private String username;
	@NotBlank(message = "Il campo 'oldPassword' non è valido")
	private String oldPassword;
	@NotBlank(message = "Il campo 'newPassword' non è valido")
	private String newPassword;

}
