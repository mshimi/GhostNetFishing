package com.example.ghostfishingnet.app.util.validators;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("customTelephoneValidator")
public class CustomTelephoneValidator implements Validator<String> {

    private static final String TELEPHONE_PATTERN = "^\\+?[0-9]{1,3}[\\s-]?[0-9]{3,14}$";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value == null || value.isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefonnummer ist erforderlich", "");
            throw new ValidatorException(msg);
        }

        if(value.length() < 6){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültiges Telefonnummerformat", "");
            throw new ValidatorException(msg);
        }

        if (!value.matches(TELEPHONE_PATTERN)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültiges Telefonnummerformat", "");
            throw new ValidatorException(msg);
        }
    }
}