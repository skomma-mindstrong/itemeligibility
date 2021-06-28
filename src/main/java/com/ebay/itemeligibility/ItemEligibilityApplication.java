package com.ebay.itemeligibility;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ItemEligibilityApplication extends SpringBootServletInitializer
{
	public static void main(String[] args)
	{
		new ItemEligibilityApplication().configure(new SpringApplicationBuilder(ItemEligibilityApplication.class)).run(args);
	}
}