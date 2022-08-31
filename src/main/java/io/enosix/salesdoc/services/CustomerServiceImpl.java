package io.enosix.salesdoc.services;

import io.enosix.salesdoc.dtos.CustomerSearchResponse;
import io.enosix.salesdoc.dtos.SearchResposne;
import io.enosix.salesdoc.resources.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final RestTemplate restTemplate;
    private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final String SEARCH_BODY = """
            {
            "searchParams": {
              "customerName": "Hib*"
            },
            "pagingOptions": {
              "pageSize": "%s",
              "pageNumber": "%s",
              "sortFields": [
                {
                  "precedence": 1,
                  "fieldName": "customerName",
                  "sortDirection": "ascending"
                }
              ]
            }
            }
            """;

    @Autowired
    public CustomerServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Customer> getCustomers(int pageNumber, int pageSize) {

        var currentRequest = String.format(SEARCH_BODY, pageSize, pageNumber);

        ResponseEntity<CustomerSearchResponse> customerResponseEntity = restTemplate.postForEntity("/sap/SAPECC/EnosixCustomer/search",
                currentRequest,
                CustomerSearchResponse.class);

        return Objects.requireNonNull(customerResponseEntity.getBody()).results;
    }
}
