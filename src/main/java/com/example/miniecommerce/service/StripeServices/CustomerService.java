package com.example.miniecommerce.service.StripeServices;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSearchResult;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {


    public static Customer createCustomer(String name, String email) throws StripeException {

        CustomerCreateParams customerCreateParams = CustomerCreateParams.builder()
                .setName(name)
                .setEmail(email)
                .build();

        return  Customer.create(customerCreateParams);

    }

    public static Customer customerSearch(String email, String name) throws StripeException {
        CustomerSearchParams params =
                CustomerSearchParams
                        .builder()
                        .setQuery("email:'" + email + "'")
                        .build();

        CustomerSearchResult result = Customer.search(params);

        Customer customer;

        if (result.getData().isEmpty()) {
            customer = createCustomer(name,email);
        } else
            customer = result.getData().getFirst();

        return customer;


    }

}
