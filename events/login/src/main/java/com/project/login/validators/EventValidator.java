package com.project.login.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// Replace occurences of "Event" with a valid model
import com.project.login.models.Event;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EventValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return Event.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Event event = (Event) object;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			event.setDate(format.parse(event.getsDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Date today = new Date();
		if(event.getDate().before(today)){
			errors.rejectValue("date", "Before");
		}
		
		// Example:
		// if(!Event.getSomeField().equals(Event.getSomeOtherField())){
		// 	errors.rejectValue("someField","Match");
		// }
	}
}
