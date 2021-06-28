package com.ebay.itemeligibility.service;

import com.ebay.itemeligibility.model.Category;
import com.ebay.itemeligibility.model.Seller;
import com.ebay.itemeligibility.model.WebServiceResponse;

import java.util.List;

public interface SellerService {

    WebServiceResponse<List<Seller>> getSellers();

    WebServiceResponse<String> addSeller(Seller seller);

    WebServiceResponse<String> enrollSeller(String sellerId,boolean enrolled);
}
