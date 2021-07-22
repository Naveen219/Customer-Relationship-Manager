package com.computergeek.crm.controller;

import com.computergeek.crm.entity.Customer;
import com.computergeek.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    // add an initbinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
    // executes for every request to the controller

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        // Removes all the leading and trailing whitespaces
        // parameter 'true' signifies : if the string is empty, set the value of the string to 'null'
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // registering the string class in the dataBinder
        // It implies for every string data, do the trimming and set it to null if the string is empty
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "index";
    }
    @GetMapping("/customer-form")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }
    @PostMapping("/customer-add")
    public String addCustomer(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {
       if (theBindingResult.hasErrors()) {
           return "customer-form";
       }
       customerService.addCustomer(theCustomer);
       return "redirect:/";
    }

    @GetMapping("/customer-update/{id}")
    public String updateCustomer(@PathVariable(value = "id") Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer-update-form";
    }

    @GetMapping("/customer-delete/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/";
    }
}
