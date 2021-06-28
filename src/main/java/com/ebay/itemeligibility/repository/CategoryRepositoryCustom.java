package com.ebay.itemeligibility.repository;

import com.ebay.itemeligibility.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<Category> getCategoriesByCategory(int category);
}
