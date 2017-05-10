package com.dreamholiday.areas.users.annotations;

import com.dreamholiday.areas.users.models.bindingModels.AddUserBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordsMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {

    @Override
    public void initialize(IsPasswordsMatching isPasswordsMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if(userClass instanceof AddUserBindingModel){
            return ((AddUserBindingModel) userClass).getPassword().equals(((AddUserBindingModel) userClass).getConfirmPassword());
        }

        return false;
    }
}
