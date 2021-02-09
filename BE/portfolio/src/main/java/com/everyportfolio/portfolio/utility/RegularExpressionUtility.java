package com.everyportfolio.portfolio.utility;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegularExpressionUtility {

    public boolean emailPatternMatch(String email) {
        Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

        return pattern.matcher(email).matches();
    }
}
