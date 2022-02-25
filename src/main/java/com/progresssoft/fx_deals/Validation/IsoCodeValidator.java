package com.progresssoft.fx_deals.Validation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;
import java.util.Set;

public class IsoCodeValidator implements ConstraintValidator<IsoCode, String> {

    Logger logger = LoggerFactory.getLogger(IsoCodeValidator.class);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean containsIsoCode = false;

        Set<Currency> currencies = Currency.getAvailableCurrencies();
        try {
            containsIsoCode = currencies.contains(Currency.getInstance(s));
        }catch(IllegalArgumentException e){
            logger.error("error occurred while validating currency Ex: ", e);
        }
        return containsIsoCode;
    }
}
