package com.ebay.itemeligibility.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@Document(collection = "category")

public class Category {
    @Id
    private String _id;
    private int category;
    private String categoryName;
    private boolean preApproved;


    public String get_id() {
        return _id;
    }

    public Category set_id(String _id) {
        this._id = _id;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public Category setCategory(int category) {
        this.category = category;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Category setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public boolean isPreApproved() {
        return preApproved;
    }

    public Category setPreApproved(boolean preApproved) {
        this.preApproved = preApproved;
        return this;
    }

}
