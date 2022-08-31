package io.enosix.salesdoc.controllers;

import io.enosix.salesdoc.resources.Customer;
import io.enosix.salesdoc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GreetingController {

    private CustomerService customerService;

    @Autowired
    public GreetingController(CustomerService customerService) {
        this.customerService = customerService;
    }



    @GetMapping("/customers")
    public String greeting(@RequestParam(name="page", required=false, defaultValue = "1") int page, Model model) {

        List<Customer> customers = this.customerService.getCustomers(page, 100);
        model.addAttribute("customers", customers);
        return "customerList";
    }

    @GetMapping("/customers/{id}")
    public String customerDetails(@PathVariable(name="id", required = true) String customerId, Model model) {
        model.addAttribute("title", String.format("Customer %s",customerId));
        // Make a call to something else for this
        return "customerDetails";
    }
}
