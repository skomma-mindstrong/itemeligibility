package com.ebay.itemeligibility.config;

import com.ebay.itemeligibility.controller.CategoryController;
import com.ebay.itemeligibility.controller.ItemController;
import com.ebay.itemeligibility.controller.SellerController;
import com.ebay.itemeligibility.exception.GenericExceptionMapper;
import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.server.ResourceConfig;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/itemeligibility")
public class ItemEligibilityConfig extends ResourceConfig {

    @PostConstruct
    public void init() {
        register(ItemController.class);
        register(CategoryController.class);
        register(SellerController.class);
        register(GenericExceptionMapper.class);
    }
}