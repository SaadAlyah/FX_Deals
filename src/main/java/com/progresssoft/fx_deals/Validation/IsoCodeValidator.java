package com.progresssoft.fx_deals.Validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;
import java.util.Set;

public class IsoCodeValidator implements ConstraintValidator<IsoCode, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean containsIsoCode = false;

        Set<Currency> currencies = Currency.getAvailableCurrencies();
        try {
            containsIsoCode = currencies.contains(Currency.getInstance(s));
        }catch(IllegalArgumentException e){
        }
        return containsIsoCode;
    }
}
