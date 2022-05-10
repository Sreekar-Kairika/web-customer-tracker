package com.springdemo.controller;

import com.springdemo.dao.CustomerDAO;
import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //inject customer service

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String lisTCustomers(Model theModel){
        //get the customer from DAO
        List<Customer> theCustomers = customerService.getCustomers();
        //add customers to the Model
        theModel.addAttribute("customers",theCustomers);
        //Add customer to the Model
        return "list-customer";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        Customer theCustomer = new Customer();

        theModel.addAttribute("customer",theCustomer);

        return "customer-form";
    }
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){

        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel){

        // get the customer from Service
        Customer theCustomer = customerService.getCustomer(theId);
        //set customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer",theCustomer);
        //send over to our form
        return "customer-form";

    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId,
                               Model theModel){

        Customer theCustomer = customerService.getCustomer(theId);

        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }


}
