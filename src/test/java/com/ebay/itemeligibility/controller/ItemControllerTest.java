package com.ebay.itemeligibility.controller;

import com.ebay.itemeligibility.ItemEligibilityApplication;
import com.ebay.itemeligibility.model.*;
import com.ebay.itemeligibility.model.error.ErrorEnum;
import com.ebay.itemeligibility.model.error.WebServiceError;
import com.ebay.itemeligibility.repository.CategoryRepository;
import com.ebay.itemeligibility.repository.SellerRepository;
import com.ebay.itemeligibility.service.CategoryService;
import com.ebay.itemeligibility.service.ItemService;
import com.ebay.itemeligibility.service.SellerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ItemEligibilityApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemControllerTest {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public SellerService sellerService;

    @Autowired
    public ItemService itemService;

    @Autowired
    public SellerRepository sellerRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @BeforeAll
    void setUp() {
        Category category1 = new Category().setCategory(1).setCategoryName("books").setPreApproved(false);

        categoryService.addCategory(category1);
        System.out.println("Added category 1");

        Category category2 = new Category().setCategory(2).setCategoryName("phones").setPreApproved(true);
        categoryService.addCategory(category2);

        Seller seller1 = new Seller().setName("SellerABC").setEnrolled(true);
        sellerService.addSeller(seller1);

        Seller seller2 = new Seller().setName("SellerBEF").setEnrolled(false);
        sellerService.addSeller(seller2);

        Seller seller3 = new Seller().setName("SellerBED").setEnrolled(true);
        sellerService.addSeller(seller3);

    }

    @AfterAll
    void tearDown() {
        sellerRepository.deleteAll();;
        categoryRepository.deleteAll();
    }


    @Test
    void checkEligibilitySucces() {
        Item item = new Item().setTitle("phone1").setSellerName("SellerBED").setCategory(2).setPrice(1000.00);
        WebServiceResponse<EligibilityResponse> response = itemService.checkEligibility(item);
        EligibilityResponse eligibility = (EligibilityResponse) response.getData();
        assertEquals(eligibility.isEligibile(),true);
        assertEquals(eligibility.getTitle(),"phone1");
    }

    @Test
    void checkEligibilityFailureNoSellerFound() {
        Item item = new Item().setTitle("phone1").setSellerName("SellerBEf").setCategory(2).setPrice(1000.00);
        WebServiceResponse response = itemService.checkEligibility(item);
        WebServiceError error = (WebServiceError) response.getError();
        assertEquals(error.getCode(), ErrorEnum.BAD_REQUEST_ELIGIBILITY_CHECK_SELLER.getCode());
        assertEquals(error.getMessage(),ErrorEnum.BAD_REQUEST_ELIGIBILITY_CHECK_SELLER.getMessage());
    }

    @Test
    void checkEligibilityFailureNoCategoryFound() {
        Item item = new Item().setTitle("phone1").setSellerName("SellerBED").setCategory(3).setPrice(1000.00);
        WebServiceResponse response = itemService.checkEligibility(item);
        WebServiceError error = (WebServiceError) response.getError();
        assertEquals(error.getCode(), ErrorEnum.BAD_REQUEST_ELIGIBILITY_CHECK_CATEGORY.getCode());
        assertEquals(error.getMessage(),ErrorEnum.BAD_REQUEST_ELIGIBILITY_CHECK_CATEGORY.getMessage());
    }

    @Test
    void checkEligibilityNotEligibleSeller() {
        Item item = new Item().setTitle("phone1").setSellerName("SellerBEF").setCategory(2).setPrice(1000.00);
        WebServiceResponse<EligibilityResponse> response = itemService.checkEligibility(item);
        EligibilityResponse eligibility = (EligibilityResponse) response.getData();
        assertEquals(eligibility.isEligibile(),false);
        assertEquals(eligibility.getTitle(),"phone1");
    }

    @Test
    void checkEligibilityNotEligibleCategory() {
        Item item = new Item().setTitle("phone1").setSellerName("SellerBED").setCategory(1).setPrice(1000.00);
        WebServiceResponse<EligibilityResponse> response = itemService.checkEligibility(item);
        EligibilityResponse eligibility = (EligibilityResponse) response.getData();
        assertEquals(eligibility.isEligibile(),false);
        assertEquals(eligibility.getTitle(),"phone1");
    }

    @Test
    void checkEligibilityNotEligiblePrice() {
        Item item = new Item().setTitle("phone1").setSellerName("SellerBED").setCategory(2).setPrice(10.00);
        WebServiceResponse<EligibilityResponse> response = itemService.checkEligibility(item);
        EligibilityResponse eligibility = (EligibilityResponse) response.getData();
        assertEquals(eligibility.isEligibile(),false);
        assertEquals(eligibility.getTitle(),"phone1");
    }


}