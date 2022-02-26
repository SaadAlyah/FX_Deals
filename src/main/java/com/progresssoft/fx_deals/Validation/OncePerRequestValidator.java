package com.progresssoft.fx_deals.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class OncePerRequestValidator extends PropertyEditorSupport {

    Logger logger = LoggerFactory.getLogger(OncePerRequestValidator.class);

    Object value;

    @Override
    public void setValue(Object value) {
        if (value instanceof String[]) {
            String[] strings = (String[])value;
            Set<String> unique = new HashSet<>(Arrays.asList(strings));//Sets.newHashSet(strings);
            this.value = unique.toArray();
        } else {
            this.value = value;
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.value = text;
    }

    @Override
    public String getAsText() {
        return value.toString();
    }

    @Override
    public Object getValue() {
        return value;
    }

}
