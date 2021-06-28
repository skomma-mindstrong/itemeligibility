package com.ebay.itemeligibility.repository.impl;

import com.ebay.itemeligibility.model.Seller;
import com.ebay.itemeligibility.repository.SellerRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class SellerRepositoryCustomImpl implements SellerRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * List all the sellers By the name not by the Id
     * @param name string representing the seller name
     * @return List of sellers with the name
     */
    @Override
    public List<Seller> getSellersByName(String name) {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(name);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Seller.class);
    }
}
