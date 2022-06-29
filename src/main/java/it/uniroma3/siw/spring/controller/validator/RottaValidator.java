package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.furgoni.model.Rotta;
import it.uniroma3.siw.spring.furgoni.service.RottaService;

@Component
public class RottaValidator implements Validator{

	@Autowired
	private	RottaService rottaService;

	private static final Logger logger = LoggerFactory.getLogger(RottaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {

		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "data", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceRotta", "required");
		

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.rottaService.alreadyExists((Rotta)o)) 
				errors.rejectValue("user", "duplicate");
			else if(this.rottaService.alreadyTake((Rotta)o))
					errors.rejectValue("furgone","duplicate" );

			
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Rotta.class.equals(aClass);
	}


}
