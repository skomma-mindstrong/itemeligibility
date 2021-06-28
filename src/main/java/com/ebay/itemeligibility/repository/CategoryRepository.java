package com.ebay.itemeligibility.repository;

import com.ebay.itemeligibility.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category,String>,CategoryRepositoryCustom    {
}
