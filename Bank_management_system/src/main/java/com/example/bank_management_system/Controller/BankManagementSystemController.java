package com.example.bank_management_system.Controller;

import com.example.bank_management_system.ApiResponse.ApiResponse;
import com.example.bank_management_system.Model.BankManagementSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/bank-management-system")
public class BankManagementSystemController {

    ArrayList<BankManagementSystem> customers = new ArrayList<>();

    @PostMapping("/add-customer")
    public ApiResponse addCustomer(@RequestBody BankManagementSystem customer) {
        for (BankManagementSystem c : customers) {
            if (Objects.equals(customer.getID(), c.getID())) {
                return new ApiResponse("There is already a customer with the same ID");
            }
        }
        customers.add(customer);
        return new ApiResponse("Customer Added Successfully");
    }

    @GetMapping("/get-customers")
    public ArrayList<BankManagementSystem> getCustomers() {
        return customers;
    }

    @PutMapping("/update-customer/{id}")
    public ApiResponse updateCustomer(@RequestBody BankManagementSystem customer,@PathVariable String id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID().equals(id)){
                customers.set(i, customer);
                return new ApiResponse("Customer Updated Successfully");
            }
        }
        return new ApiResponse("Customer Not Found");
    }

    @DeleteMapping("/delete-customer/{id}")
    public ApiResponse deleteCustomer(@PathVariable String id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID().equals(id)){
                customers.remove(i);
                return new ApiResponse("Customer Deleted Successfully");
            }
        }
        return new ApiResponse("Customer Not Found");
    }

    @PutMapping("/deposit/{id}/{amount}")
    public ApiResponse deposit(@PathVariable String id,@PathVariable double amount) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID().equals(id)){
                customers.get(i).setBalance(customers.get(i).getBalance()+amount);
                return new ApiResponse("Deposited Successfully");
            }
        }
        return new ApiResponse("Customer Not Found");
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public ApiResponse withdraw(@PathVariable String id,@PathVariable double amount) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getID().equals(id)){
                if (customers.get(i).getBalance()-amount>=0){
                    customers.get(i).setBalance(customers.get(i).getBalance()-amount);
                    return new ApiResponse("Withdrawal Successfully");
                }
                return new ApiResponse("you don't have enough money in your balance");
            }
        }
        return new ApiResponse("Customer Not Found");
    }


}
