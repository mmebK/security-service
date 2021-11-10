package com.med.web;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.med.entity.EventUser;
import com.med.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/register")
    public EventUser register(@RequestBody UserForm userForm) {

        return accountService.saveUser(userForm.getUserName(), userForm.getEmail(), userForm.getPassWord());
    }

    @PostMapping("/addRole")
    public void addRoleToUser(@RequestBody ObjectNode objectNode) {
        String userName = objectNode.get("userName").asText();
        String role = objectNode.get("role").asText();
        accountService.addRoleToUser(userName, role);
    }


}

@Data
class UserForm {
    private String userName;
    private String passWord;
    private String email;
}

