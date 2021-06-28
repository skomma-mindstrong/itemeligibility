package com.ebay.itemeligibility.service.impl;

import com.ebay.itemeligibility.config.ConfigProperties;
import com.ebay.itemeligibility.model.*;
import com.ebay.itemeligibility.model.error.ErrorEnum;
import com.ebay.itemeligibility.model.error.WebServiceError;
import com.ebay.itemeligibility.repository.CategoryRepository;
import com.ebay.itemeligibility.repository.SellerRepository;
import com.ebay.itemeligibility.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ItemServiceImpl implements ItemService {


    @Autowired
    ConfigProperties configProp;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public SellerRepository sellerRepository;

    /**
     * Service method for checking the eligibility of the item
     * @param item Request object for with the details of the item for which tje ligibility has to be checked
     * @return Eligibility response object indicating whether the item is eligible or not
     */
    @Override
    public WebServiceResponse<EligibilityResponse> checkEligibility(Item item) {


        if(item.getTitle() == null || item.getSellerName()== null || item.getCategory() == 0 || item.getPrice() == 0.0){
            return new WebServiceResponse(new WebServiceError(ErrorEnum.BAD_REQUEST_ADD_ITEM.getCode(), ErrorEnum.BAD_REQUEST_ADD_ITEM.getMessage()));
        }

        List<Category> categoryList = categoryRepository.getCategoriesByCategory(item.getCategory());

        if(categoryList == null || categoryList.isEmpty()){
            return new WebServiceResponse(new WebServiceError(ErrorEnum.BAD_REQUEST_ELIGIBILITY_CHECK_CATEGORY.getCode(), ErrorEnum.BAD_REQUEST_ELIGIBILITY_CHECK_CATEGORY.getMessage()));
        }

        if(!categoryList.get(0).isPreApproved()){
            return new WebServiceResponse<EligibilityResponse>(new EligibilityResponse().setEligibile(false).setTitle(item.getTitle()));
        }

        List<Seller> sellerList = sellerRepository.getSellersByName(item.getSellerName());

        if(sellerList == null || sellerList.isEmpty()){
            return new WebServiceResponse(new WebServiceError(ErrorEnum.BAD_REQUEST_ELIGIBILITY_CHECK_SELLER.getCode(), ErrorEnum.BAD_REQUEST_ELIGIBILITY_CHECK_SELLER.getMessage()));
        }

        if(!sellerList.get(0).isEnrolled()){
            return new WebServiceResponse<EligibilityResponse>(new EligibilityResponse().setEligibile(false).setTitle(item.getTitle()));
        }


        if(item.getPrice() < Double.parseDouble(configProp.getConfigValue("minimum.price"))){
            return new WebServiceResponse<EligibilityResponse>(new EligibilityResponse().setEligibile(false).setTitle(item.getTitle()));
        }

        return new WebServiceResponse<EligibilityResponse>(new EligibilityResponse().setEligibile(true).setTitle(item.getTitle()));
    }
}