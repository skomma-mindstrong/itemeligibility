package com.ebay.itemeligibility.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@Document(collection = "seller")
public class Seller {

    @Id
    private String _id;
    private String name;
    private boolean enrolled;

    public String get_id() {
        return _id;
    }

    public Seller set_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Seller setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public Seller setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
        return this;
    }

}
