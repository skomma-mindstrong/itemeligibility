package com.ebay.itemeligibility.repository.impl;

import com.ebay.itemeligibility.model.Category;
import com.ebay.itemeligibility.model.Seller;
import com.ebay.itemeligibility.repository.CategoryRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * List all the categories By the category not by the Id
     * @param category integer reporesenting the category
     * @return list of categories
     */
    @Override
    public List<Category> getCategoriesByCategory(int category) {
        Query query = new Query();
        Criteria criteria = Criteria.where("category").is(category);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Category.class);
    }
}
