package it.dedagroup.project_cea.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.ChangePasswordDtoRequest;
import it.dedagroup.project_cea.dto.request.RegisterUserDTORequest;
import it.dedagroup.project_cea.dto.response.ChangePasswordDTOResponse;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.exception.model.UserNotFoundException;
import it.dedagroup.project_cea.mapper.UserMapper;
import it.dedagroup.project_cea.model.User;
import it.dedagroup.project_cea.service.def.AdministratorServiceDef;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;
import it.dedagroup.project_cea.service.def.SecretaryServiceDef;
import it.dedagroup.project_cea.service.def.TechnicianServiceDef;
import it.dedagroup.project_cea.service.def.UserServiceDef;

@Service
public class AllFacade {

	@Autowired
	private UserServiceDef userService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AdministratorServiceDef administratorService;
	@Autowired
	private SecretaryServiceDef secretaryService;
	@Autowired
	private CustomerServiceDef customerService;
	@Autowired
	private TechnicianServiceDef techService;

	public String registerUser(RegisterUserDTORequest user) {
		userService.addUser(userMapper.toUser(user));
		return "Registrazione avvenuta con successo";
	}

	public ChangePasswordDTOResponse changePassword(ChangePasswordDtoRequest request) {
		if (!request.getNewPassword().equals(request.getConfirmPassword()))
			new NotValidDataException("La password di conferma non è uguale");
		// CAMBIO PASSWORD CUSTOMER

		ChangePasswordDTOResponse response = new ChangePasswordDTOResponse();
		try {
			User user = null;
			user = administratorService.findByUsername(request.getUsername());
			if (user == null)
				user = secretaryService.findByUsername(request.getUsername());
			if (user == null)
				user = techService.findTechnicianByUsername(request.getUsername());
			if (user == null)
				user = customerService.findCustomerByUsername(request.getUsername()); 
			
			response.setOldPassword(user.getPassword());
			response.setNewPassword(request.getConfirmPassword());
			response.setUsername(request.getUsername());
			user.setPassword(request.getConfirmPassword());
			userService.changePassword(user);
		} catch (Exception e) {
			throw new UserNotFoundException("User not found with username: " + request.getUsername());
		}

		return response;

	}

}
