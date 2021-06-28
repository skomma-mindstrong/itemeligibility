package com.ebay.itemeligibility.service.impl;

import com.ebay.itemeligibility.model.Seller;
import com.ebay.itemeligibility.model.error.ErrorEnum;
import com.ebay.itemeligibility.model.error.WebServiceError;
import com.ebay.itemeligibility.model.WebServiceResponse;
import com.ebay.itemeligibility.repository.SellerRepository;
import com.ebay.itemeligibility.service.SellerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    public SellerRepository sellerRepository;

    /**
     * Returns the list of sellers saved in the system
     * @return List of all the sellers in the system
     */
    @Override
    public WebServiceResponse<List<Seller>> getSellers() {
        return new WebServiceResponse<List<Seller>>(sellerRepository.findAll());
    }

    /**
     * Add a new seller into the database
     * @param seller Object of the seller to be saved
     * @return the auto generated id of the seller saved in the database
     */
    @Override
    public WebServiceResponse<String> addSeller(Seller seller) {
        if(!sellerRepository.getSellersByName(seller.getName()).isEmpty()){
            return new WebServiceResponse(new WebServiceError(ErrorEnum.SELLER_DUPLICATE.getCode(), ErrorEnum.SELLER_DUPLICATE.getMessage()));
        }
        return new WebServiceResponse<String>(sellerRepository.save(seller).get_id().toString());
    }

    /**
     * Edit the enroll status for a seller
     * @param sellerId id of the seller to be edited for the enroll status
     * @param enrolled boolean indicating the status to be modified to
     * @return the id of the seller for which the status is modified
     */
    @Override
    public WebServiceResponse<String> enrollSeller(String sellerId,boolean enrolled) {
        try{
            Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);
            if(!sellerOptional.isPresent()){
                return new WebServiceResponse(new WebServiceError(ErrorEnum.SELLER_NOT_FOUND.getCode(), ErrorEnum.SELLER_NOT_FOUND.getMessage()));
            }
            Seller seller = sellerOptional.get();
            if(enrolled){
                if(seller.isEnrolled()){
                    return new WebServiceResponse(new WebServiceError(ErrorEnum.SELLER_ALREADY_ENROLLED.getCode(), ErrorEnum.SELLER_ALREADY_ENROLLED.getMessage()));
                }
            }else{
                if(!seller.isEnrolled()){
                    return new WebServiceResponse(new WebServiceError(ErrorEnum.SELLER_NOT_ENROLLED.getCode(), ErrorEnum.SELLER_NOT_ENROLLED.getMessage()));
                }
            }
            return new WebServiceResponse<String>(sellerRepository.save(seller.setEnrolled(enrolled)).get_id().toString());
        }catch(Exception e){
            return new WebServiceResponse(new WebServiceError(ErrorEnum.INTERNAL_ERROR.getCode(), ErrorEnum.INTERNAL_ERROR.getMessage()));
        }

    }




}
