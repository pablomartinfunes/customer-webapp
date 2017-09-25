package com.pfunes.controller;

import com.pfunes.model.Customer;
import com.pfunes.model.CustomerDAO;
import com.pfunes.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pfunes on 15/09/17.
 */

@Controller
public class CustomerController {

    @Autowired
    private CustomerDAO customerDAO;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping("/saludo")
    public String saludo(@RequestParam(value = "name", defaultValue="World") String name, Model model){
        model.addAttribute("name", name);
        return "saludo";
    }

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model){
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting){
        return "result";
    }

    @GetMapping("/customer/all")
    public String allCustomers(Model model){
        List<Customer> customers = customerDAO.findAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam(value = "id") Long id){
        logger.info("Deleting user: " + customerDAO.findOne(id));
        customerDAO.delete(id);
        return "redirect:/customer/all";
    }

    @GetMapping("/updateCustomer")
    public String showUpdateCustomer(@RequestParam(value = "id") Long id, Model model){

        Customer customer = customerDAO.findOne(id);
        logger.info("Customer finded: " + customer);
        model.addAttribute("customer", customer);
        return "update-customer";
    }

    @PostMapping("/updateCustomer")
    public String procesUpdateCustomer(@ModelAttribute Customer customer){

        customerDAO.save(customer);
        logger.info("Customer updated: " + customer);
        return "redirect:/customer/all";
    }

    @RequestMapping("/saveCustomer")
    public String saveCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "update-customer";
    }

}
