package com.ebay.itemeligibility.service.impl;

import com.ebay.itemeligibility.model.Category;
import com.ebay.itemeligibility.model.Seller;
import com.ebay.itemeligibility.model.WebServiceResponse;
import com.ebay.itemeligibility.model.error.ErrorEnum;
import com.ebay.itemeligibility.model.error.WebServiceError;
import com.ebay.itemeligibility.repository.CategoryRepository;
import com.ebay.itemeligibility.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    /**
     * Listing all the categories in the system
     * @return List of categories stored in the system
     */
    @Override
    public WebServiceResponse<List<Category>> getCategories() {
        return new WebServiceResponse<List<Category>>(categoryRepository.findAll());
    }

    /**
     * Service method to add a new Category
     * @param category category object to be saved
     * @return the Id of the category added
     */
    @Override
    public WebServiceResponse<String> addCategory(Category category) {
        if(!categoryRepository.getCategoriesByCategory(category.getCategory()).isEmpty()){
            return new WebServiceResponse(new WebServiceError(ErrorEnum.CATEGORY_DUPLICATE.getCode(), ErrorEnum.CATEGORY_DUPLICATE.getMessage()));
        }
        return new WebServiceResponse<String>(categoryRepository.save(category).get_id().toString());
    }

    /**
     * To Edit the approval status of a category
     * @param categoryId id of the category to change the approval status
     * @param preApproved approval status to be modified to
     * @return the id of the category for which the pproval status is modified
     */
    @Override
    public WebServiceResponse<String> approveCategory(String categoryId,boolean preApproved) {
        try{
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if(!categoryOptional.isPresent()){
                return new WebServiceResponse(new WebServiceError(ErrorEnum.CATEGORY_NOT_FOUND.getCode(), ErrorEnum.CATEGORY_NOT_FOUND.getMessage()));
            }
            Category category = categoryOptional.get();
            if(preApproved){
                if(category.isPreApproved()){
                    return new WebServiceResponse(new WebServiceError(ErrorEnum.CATEGORY_ALREADY_APPROVED.getCode(), ErrorEnum.CATEGORY_ALREADY_APPROVED.getMessage()));
                }
            }else{
                if(!category.isPreApproved()){
                    return new WebServiceResponse(new WebServiceError(ErrorEnum.CATEGORY_NOT_APPROVED.getCode(), ErrorEnum.CATEGORY_NOT_APPROVED.getMessage()));
                }
            }

            return new WebServiceResponse<String>(categoryRepository.save(category.setPreApproved(preApproved)).get_id().toString());
        }catch(Exception e){
            return new WebServiceResponse(new WebServiceError(ErrorEnum.INTERNAL_ERROR.getCode(), ErrorEnum.INTERNAL_ERROR.getMessage()));
        }

    }
}
