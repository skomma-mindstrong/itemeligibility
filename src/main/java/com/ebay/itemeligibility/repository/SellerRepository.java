package com.ebay.itemeligibility.repository;

import com.ebay.itemeligibility.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller,String>,SellerRepositoryCustom {
}
