package com.ebay.itemeligibility.repository;

import com.ebay.itemeligibility.model.Seller;

import java.util.List;

public interface SellerRepositoryCustom {

    List<Seller> getSellersByName(String name);

}
