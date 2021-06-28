package com.ebay.itemeligibility.service;

import com.ebay.itemeligibility.model.EligibilityResponse;
import com.ebay.itemeligibility.model.Item;
import com.ebay.itemeligibility.model.WebServiceResponse;

import java.util.List;

public interface ItemService {

    WebServiceResponse<EligibilityResponse> checkEligibility(Item item);
}
