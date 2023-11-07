package it.dedagroup.project_cea.controller;

import static it.dedagroup.project_cea.util.UtilPath.CHANGE_PASSWORD_PATH;
import static it.dedagroup.project_cea.util.UtilPath.LOGIN_PATH;
import static it.dedagroup.project_cea.util.UtilPath.REGISTER_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.dto.request.ChangePasswordDtoRequest;
import it.dedagroup.project_cea.dto.request.LoginDTORequest;
import it.dedagroup.project_cea.dto.request.RegisterUserDTORequest;
import it.dedagroup.project_cea.dto.response.ChangePasswordDTOResponse;
import it.dedagroup.project_cea.dto.response.LoginDTOResponse;
import it.dedagroup.project_cea.facade.AllFacade;
import it.dedagroup.project_cea.model.User;
import jakarta.validation.Valid;

@RestController
public class AllController {
	
	@Autowired
	private AllFacade allFacade;
	
	@PostMapping(LOGIN_PATH)
	public ResponseEntity<LoginDTOResponse> login(@Valid @RequestBody LoginDTORequest request){
		return null;
	}
	
	@PostMapping(REGISTER_PATH)
	public ResponseEntity<String> registerAccount(@Valid @RequestBody RegisterUserDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allFacade.registerUser(request));
	}
	
	@PostMapping(CHANGE_PASSWORD_PATH)
	public ResponseEntity<ChangePasswordDTOResponse> registerAccount(@Valid @RequestBody ChangePasswordDtoRequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allFacade.changePassword(request));
	}

	

}
