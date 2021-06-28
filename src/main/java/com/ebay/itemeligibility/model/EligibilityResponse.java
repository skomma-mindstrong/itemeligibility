package com.ebay.itemeligibility.model;

/**
 * Successful response class for an item eligibility
 */
public class EligibilityResponse {

    private boolean eligibile;
    private String title;

    public boolean isEligibile() {
        return eligibile;
    }

    public EligibilityResponse setEligibile(boolean eligibile) {
        this.eligibile = eligibile;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EligibilityResponse setTitle(String title) {
        this.title = title;
        return this;
    }


}
