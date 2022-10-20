package com.example.appapioracle.Service;

import com.example.appapioracle.Entity.Customers;
import com.example.appapioracle.Repository.CustomersRepository;
import com.example.appapioracle.payload.ApiResponse;
import com.example.appapioracle.payload.ResCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersService {
    @Autowired
    private CustomersRepository customersRepository;

    public List<Customers> getCustomers(){
        return customersRepository.findAll();
    }

    public Customers getCustomer(Long id){
        Optional<Customers> byId = customersRepository.findById(id);
        return byId.orElse(null);
    }
    public ApiResponse addCustomer(ResCustomers resCustomers){
        boolean b = customersRepository.existsByPhoneNumber(resCustomers.getPhoneNumber());
        if (b){
           return new ApiResponse("bunday mijoz mavjud",false);
        }
        Customers  customers = new Customers();
        customers.setFullName(resCustomers.getFullName());
        customers.setPhoneNumber(resCustomers.getPhoneNumber());
        customers.setAddress(resCustomers.getAddress());
        customersRepository.save(customers);
        return new ApiResponse("Mijoz saqlandi", true);
    }
    public ApiResponse editCustomer(Long id, ResCustomers resCustomers){
        boolean existsBy = customersRepository.existsByPhoneNumberAndIdNot(resCustomers.getPhoneNumber(), id);
        if (existsBy){
            return new ApiResponse("Bunday telefon raqam bazada mavjud",false);
        }
        Optional<Customers> byId = customersRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Bunday mijoz mavjud emas",false);
        }
        Customers customers = byId.get();
        customers.setFullName(resCustomers.getFullName());
        customers.setPhoneNumber(resCustomers.getPhoneNumber());
        customers.setAddress(resCustomers.getAddress());
        customersRepository.save(customers);
        return new ApiResponse("Mijod taxrirlandi",true);

    }
    public ApiResponse deleteCustomer(Long id) {
        Optional<Customers> byId = customersRepository.findById(id);
        if (byId.isPresent()){
            customersRepository.deleteById(id);
            return new  ApiResponse("Mijoz ochirildi",true);
        }else{
            return new ApiResponse("id not found",false);
        }
    }
}
