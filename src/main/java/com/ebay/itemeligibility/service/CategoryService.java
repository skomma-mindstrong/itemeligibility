package com.ebay.itemeligibility.service;

import com.ebay.itemeligibility.model.Category;
import com.ebay.itemeligibility.model.WebServiceResponse;

import java.util.List;

public interface CategoryService {

    WebServiceResponse<List<Category>> getCategories();

    WebServiceResponse<String> addCategory(Category category);

    WebServiceResponse<String> approveCategory(String categoryId,boolean preApproved);

}
