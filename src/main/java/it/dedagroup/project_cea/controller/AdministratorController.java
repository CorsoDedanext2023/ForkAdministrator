package it.dedagroup.project_cea.controller;

import static it.dedagroup.project_cea.util.UtilPath.CREATE_APARTMENT_PATH;
import static it.dedagroup.project_cea.util.UtilPath.CREATE_CONDOMINIUM;
import static it.dedagroup.project_cea.util.UtilPath.DELETE_ADMINISTRATOR;
import static it.dedagroup.project_cea.util.UtilPath.DELETE_CONDOMINIUM;
import static it.dedagroup.project_cea.util.UtilPath.GET_CONDOMINIUM_PATH;
import static it.dedagroup.project_cea.util.UtilPath.GET_CUSTOMER_PATH;
import static it.dedagroup.project_cea.util.UtilPath.GET_SCANS_BY_CONDOMINIUM_ID_PATH;
import static it.dedagroup.project_cea.util.UtilPath.INSERT_BILL_PATH;
import static it.dedagroup.project_cea.util.UtilPath.INSERT_CONDOMINIUM_PATH;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.dedagroup.project_cea.dto.request.*;
import it.dedagroup.project_cea.dto.response.*;
import it.dedagroup.project_cea.facade.AdministratorFacade;
import it.dedagroup.project_cea.model.Administrator;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Condominium;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static it.dedagroup.project_cea.util.UtilPath.*;

@RestController
@Validated
@Tag(name = "Method Administratore", description = "Tutti i metodi che un user <Administrator> può effettuare sul sito")
public class AdministratorController {
	
	@Autowired
	private AdministratorFacade administratorFacade;
	
	//ENDPOINT DI INSERIMENTO DEL CONDOMINIO
	@Operation(summary = "Inserimento <Condominium> nel database.", description = "Questo endpoint consente l'inserimento di un nuovo condominio.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Inserimento del condominio avvenuto con successo.",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "400", description = "Errore nelle validation della richiesta. Uno o più campi della richiesta sono errati.",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ValidationErrorDTOResponse.class))),
			@ApiResponse(responseCode = "500", description = "Errore interno del server.")
	})

	/**
	 * <h2> Metodo che inserisce un condominio in database </h2>
	 *
	 *
	 * @param CondominiumDTORequest, un oggetto DTO che ha come attributi un 'id_administrator' ed un 'address'.
	 * @return Una response che indica l'esito dell'inserimento nel database. Se l'inserimento è
 *         corretto, viene restituita una stringa con un messaggio di conferma. Se ci sono errori di
 *         validazione o dati non validi, viene restituito un codice di risposta 400 BAD REQUEST.
	 */
	@PostMapping(INSERT_CONDOMINIUM_PATH)
	public ResponseEntity<String> insertCondominium(@Valid @RequestBody CondominiumDTORequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(administratorFacade.insertCondominium(request));
	}
	



	//ENDPOINT DI INSERIMENTO DELLA BOLLETTA
	@Operation(summary = "Inserimento <Bill> nel database.", description = "Questo endpoint consente l'inserimento di un nuova bolletta.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Inserimento della bolletta nel database avvenuto. Esempio di risposta: {'message':'Bolletta inserita con successo'}"),
	    @ApiResponse(responseCode = "400", description = "Errore nella richiesta. Possono verificarsi errori di validazione dei campi."),
	    @ApiResponse(responseCode = "500", description = "Errore interno del server.")
	})
	/**
	 * <h2>Metodo per l'inserimento di una bolletta nel database</h2>
	 *
	 * <pre>
	 * Metodo che al momento della creazione della bolletta, viene settato la data di pagamento a 1 mese
	 * dalla creazione della bolletta. Il costo viene generato tramite dei calcoli predefiniti, più il cliente
	 * consuma, più il costo al mc aumenta.
	 * </pre>
	 *
	 * @param BillDTORequest, un oggetto DTO che prende la lettura del contatore (id_scan) e la data di creazione della bolletta (deliveringDay)
	 * @return Una response che indica l'esito dell'inserimento nel database. Se l'inserimento è
 *         corretto, viene restituita una stringa con un messaggio di conferma. Se ci sono errori di
 *         validazione o dati non validi, viene restituito un codice di risposta 400 BAD REQUEST.
	 */
	@PostMapping(INSERT_BILL_PATH)
	public ResponseEntity<String> insertBill(@Valid @RequestBody BillDTORequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(administratorFacade.insertBill(request));
	}




	@PostMapping(CREATE_APARTMENT_PATH)
    @Operation(summary = "Inserimento <Apartment> nel database.", description = "Questo endpoint consente l'inserimento di un nuovo appartamento.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Inserimento dell' appartamento nel database avvenuto. Esempio di risposta: {'message':'Appartamento inserito con successo'}"),
	    @ApiResponse(responseCode = "400", description = "Errore nella richiesta. Possono verificarsi errori di validazione dei campi."),
	    @ApiResponse(responseCode = "500", description = "Errore interno del server.")
	})
    /**
     * <h2> Metodo per l'inserimento di un appartamento nel database </h2>
     * @param AddApartmentDtoRequest,
     * @return
     */
    public ResponseEntity<String> addApartment(@Valid @RequestBody AddApartmentDtoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body("apartment created.");
    }


	//si può cambiare in pathVariable volendo
    @PostMapping (GET_CONDOMINIUM_PATH)
    public ResponseEntity<List<CondominiumDtoResponse>> getCondominiumsOfAdministrator(@Valid @RequestBody AdministratorIdDtoRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.getCondominiumByAdministratorId(request));
    }
    


	//ENDPOINT PER VISUALIZZARE TUTTI I <CUSTOMER> DI UN DATO CONDOMINIO
    @Operation(summary = "Elenco dei clienti di un condominio.", description = "Questo endpoint restituisce l'elenco dei clienti associati a un condominio specifico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Elenco dei clienti ottenuto con successo.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerExtendedInfoDTOResponse.class)))
        ),
        @ApiResponse(responseCode = "400", description = "Condominio non trovato."),
        @ApiResponse(responseCode = "500", description = "Errore interno del server.")
    })
    @Validated
	@GetMapping(GET_CUSTOMER_PATH)
    public ResponseEntity<List<CustomerExtendedInfoDTOResponse>> getCustomerOfCondominium(@RequestParam("id") @Min(value = 1,message = "L'Id_Amministratore deve essere maggiore di 0") long id){
    	return ResponseEntity.status(HttpStatus.FOUND).body(administratorFacade.getCustomerByCondominiumId(id));
    }
    


	//   ENDPOINT DI SUDDIVISIONE BOLLETTE
	@Operation(summary = "Metodo che splitta le bollette",description = "Questo endpoint prende in input una bolletta condominiale e l'id del condominio nella quale bisogna splittarla, se l'id del condominio non coincide va in eccezione e restituisce status code 404, altrimenti restiutisce status code 200 e una stringa che conferma la suddivisione")
   @ApiResponses(value= {
		   @ApiResponse(responseCode = "200",description = "Bollette splittate correttamente",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = String.class))),
		   @ApiResponse(responseCode = "Not Found(404)",description = "Nessun condominio co questo id",content = @Content(mediaType = MediaType.ALL_VALUE))
   })
    @PostMapping("/billSplitter/{idCondominium}")
    public ResponseEntity<String> billSplitter(@PathVariable long idCondominium,@RequestBody AceaBillRequest request){
        return ResponseEntity.ok(administratorFacade.billSplitter(idCondominium, request));
    }



    @Operation(summary = "Elenco dele bollette di un condominio.", description = "Questo endpoint restituisce l'elenco delle bollette associati a un condominio specifico.")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Elenco delle bollette ottenuto con successo.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerExtendedInfoDTOResponse.class)))
        ),
        @ApiResponse(responseCode = "400", description = "Condominio non trovato."),
        @ApiResponse(responseCode = "500", description = "Errore interno del server.")
    })
	@GetMapping(GET_SCANS_BY_CONDOMINIUM_ID_PATH+"{id}")
	public ResponseEntity<List<ApartmentScanDTOResponse>> findAllScanByCondominiumId(@PathVariable("id") @Min(value = 1,message = "L'Id_Condominio deve essere maggiore di 0") long id){
		return ResponseEntity.status(HttpStatus.FOUND).body(administratorFacade.findAllScanByCondominiumId(id));
	}



	//inserire in util path
	//TODO da controllare se si necessita realmente di questo endpoint
	@PostMapping("/assign/apartment")
	public ResponseEntity<String> assignApartment(Apartment apartment){
		return null;
	}


	@Operation(summary = "Metodo per la creazione di condomini con la lista di appartamenti",
			description = "Consente in creazione del condominio di inserire anche una lista di appartamenti vuoti")
			@ApiResponses(value = {
					@ApiResponse(responseCode = "201",description = "Creazione del condominio e degli appartamenti associati avvenuta con successo",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = CondominiumDtoResponse.class ))),
					@ApiResponse(responseCode = "400",description = "Errore nell' inserimento dati nella request",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDtoResponse.class))),
			})
	@PostMapping(CREATE_CONDOMINIUM)
	public ResponseEntity<CondominiumDtoResponse> CreateCondominium(@Valid @RequestBody AddCondominiumDTORequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(administratorFacade.createCondominium(request));
	}
	/**
	 * <h2> Metodo che elimina un amministratore e non lo rende visibile </h2>
	 *
	 * <par>
	 * Metodo che prende un id di un amminsitratore e setta il boolean 'isAvailable' a 'false'
	 * in modo che i dati di un amministratore non vengano persi del tutto, ma non saranno visibili
	 * in altre chiamate di ritorno degli amministratori.
	 * </par>
	 * @param id, una variabile di tipo long 'id'
	 * @return Una response che indica l'eliminazione di un amministartore. Se l'id è stato trovato nel
	 * database, ritornernà un oggetto amministratore con la variabile 'isAvailable' settata a false. Se
	 * ci sono errori di dati non validi, viene restituito una codice di risposta di risposta 400 BAD REQUEST.
	 */

	@PostMapping(DELETE_ADMINISTRATOR)
	public ResponseEntity<Administrator> deleteAdministrator(@RequestParam("id") @Min(value = 1, message = "L'id dell'amministratore non è valido") long id){
		return ResponseEntity.status(HttpStatus.OK).body(administratorFacade.deleteAdministrator(id));
	}

	/**
	 * <h2> Metodo che elimina un condominio e non lo rende visibile </h2>
	 *
	 * <par>
	 * Metodo che prende un id di un condominio e setta il boolean 'isAvailable' a 'false'
	 * in modo che i dati di un condominio non vengano persi del tutto, ma non saranno visibili
	 * in altre chiamate di ritorno degli amministratori.
	 * </par>
	 * @param id, una variabile di tipo long 'id'
	 * @return Una response che indica l'eliminazione di un condominio. Se l'id è stato trovato nel
	 * database, ritornernà un oggetto condominio con la variabile 'isAvailable' settata a false. Se
	 * ci sono errori di dati non validi, viene restituito una codice di risposta di risposta 400 BAD REQUEST.
	 */
	@PostMapping(DELETE_CONDOMINIUM)
	public ResponseEntity<Condominium> deleteCondominium(@RequestParam("id") @Min(value = 1, message = "L'id dell'amministratore non è valido") long id){
		return ResponseEntity.status(HttpStatus.OK).body(administratorFacade.deleteCondominium(id));
	}

}
