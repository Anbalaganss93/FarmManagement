package com.farmmanagement.registerpage;

public class RegisterValidationModel {
    String errorMessage = "Invalid input";
    boolean validation = false;

    public RegisterValidationModel(String errorMessage, boolean validation) {
        this.errorMessage = errorMessage;
        this.validation = validation;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isValidation() {
        return validation;
    }
}
