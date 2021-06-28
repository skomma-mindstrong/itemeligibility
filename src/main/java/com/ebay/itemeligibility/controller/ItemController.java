package com.ebay.itemeligibility.controller;

import com.ebay.itemeligibility.model.EligibilityResponse;
import com.ebay.itemeligibility.model.Item;
import com.ebay.itemeligibility.model.WebServiceResponse;
import com.ebay.itemeligibility.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.xml.ws.Response;
import java.util.List;

    @Component
    @Path("/items")
    public class ItemController {

        @Autowired
        private ItemService itemService;


        @PUT
        @Path("/eligibilityCheck")
        @Produces(MediaType.APPLICATION_JSON)
        public WebServiceResponse<EligibilityResponse> checkEligibility(@RequestBody Item item) {
            return  itemService.checkEligibility(item);
        }

    }

