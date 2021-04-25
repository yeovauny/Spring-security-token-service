package com.tyr.domain.response;

import java.util.Date;

public class ValidateResponse {
    private boolean validate;
    private Date date;
    private String description;

    public ValidateResponse(boolean validate, Date date, String description) {
        this.validate = validate;
        this.date = date;
        this.description = description;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
