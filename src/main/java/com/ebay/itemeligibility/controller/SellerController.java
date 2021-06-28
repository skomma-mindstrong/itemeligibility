package com.ebay.itemeligibility.controller;

import com.ebay.itemeligibility.model.Category;
import com.ebay.itemeligibility.model.Seller;
import com.ebay.itemeligibility.model.WebServiceResponse;
import com.ebay.itemeligibility.service.CategoryService;
import com.ebay.itemeligibility.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/sellers")
public class SellerController {

    @Autowired
    public SellerService sellerService;

    /**
     * Get all the sellers added in the system
     * @return WebServiceResponse with all the sellers in the system
     */
    @GET
    @Path("/getSellers")
    @Produces(MediaType.APPLICATION_JSON)
    public WebServiceResponse<List<Seller>>  getItems() {
        return  sellerService.getSellers();
    }

    /**
     * To add a new seller into the system
     * @param seller Seller object for adding a new seller
     * @return WebserviceResponse id of the new seller added
     */
    @POST
    @Path("/saveSeller")
    @Produces(MediaType.APPLICATION_JSON)
    public WebServiceResponse<String> addSeller(@RequestBody Seller seller) {
        return  sellerService.addSeller(seller);
    }

    /**
     * To change the enrolled status of the seller
     * @param id of the seller to change the enrolled status
     * @param enrolled boolean indicating whether to enroll the seller or not
     * @return WebserviceReponse with the id of the seller modified
     */
    @PUT
    @Path("enrollSeller/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public WebServiceResponse<String> enrollSeller(@PathParam("id") String id ,@QueryParam("enrolled")boolean enrolled)
    {
        return  sellerService.enrollSeller(id,enrolled);
    }
}
