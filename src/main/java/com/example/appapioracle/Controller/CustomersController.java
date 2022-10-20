package com.example.appapioracle.Controller;

import com.example.appapioracle.Entity.Customers;
import com.example.appapioracle.Service.CustomersService;
import com.example.appapioracle.payload.ApiResponse;
import com.example.appapioracle.payload.ResCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @GetMapping("/getAll")
    public List<Customers> getCustomers(){
        return customersService.getCustomers();
    }
    @GetMapping("/getId/{id}")
    public Customers getCustomer(@PathVariable Long id){
        return customersService.getCustomer(id);
    }
    @PostMapping("/post")
    public ApiResponse addCustomer(@RequestBody ResCustomers resCustomers){
        return customersService.addCustomer(resCustomers);
    }
    @PutMapping("{id}")
    public ApiResponse editCustomer(@PathVariable Long id, @RequestBody ResCustomers resCustomers){
        return new ApiResponse(customersService.editCustomer(id,resCustomers));
    }
    @DeleteMapping("{id}")
    public ApiResponse deleteCustomer(@PathVariable Long id){
        return new ApiResponse(customersService.deleteCustomer(id));
    }
}
