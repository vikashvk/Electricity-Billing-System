package com.ebs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.exception.ResourceNotFoundException;
import com.ebs.model.CustomerDetail;
import com.ebs.repository.CustomerDetailRespository;
import com.ebs.security.CurrentUser;
import com.ebs.security.UserPrincipal;

/**
 * @author Poonamchand Sahu
 * 
 * */
@RestController
public class UserController {

//    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private CustomerDetailRespository customerDetailRespository;
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public CustomerDetail getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return customerDetailRespository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
