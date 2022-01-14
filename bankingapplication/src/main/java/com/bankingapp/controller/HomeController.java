package com.bankingapp.controller;

import com.bankingapp.dto.LoginDto;
import com.bankingapp.dto.UserDetailsDto;
import com.bankingapp.model.UserAccount;
import com.bankingapp.model.UserAccountDto;
import com.bankingapp.model.UserDetails;
import com.bankingapp.service.AccountTypesService;
import com.bankingapp.service.UserAccountService;
import com.bankingapp.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    UserDetails user;
    String accountTypes;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AccountTypesService accountTypesService;
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/")
    public String homepage(){
        return "Home";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        UserDetailsDto newuser = new UserDetailsDto();
        model.addAttribute("newuser", newuser);
        return "signup";
    }

    @PostMapping("/saveuser")
    public String saveUser(UserDetailsDto userDetailsDto, Model model){
        UserDetails userDetails = new UserDetails();
        userDetails.setName(userDetailsDto.getName());
        userDetails.setDateOfBirth(userDetailsDto.getDateOfBirth());
        if(userDetailsDto.getAge() < 18){
            return "notEligible";
        }
        userDetails.setAge(userDetailsDto.getAge());
        userDetails.setEmail(userDetailsDto.getEmail());
        userDetails.setPassword(userDetailsDto.getPassword());
        userDetails.setRole("user");
        userDetailsService.saveUser(userDetails);
        LoginDto loginDto = new LoginDto();
        model.addAttribute("login", loginDto);
        return "Home";
    }

    @GetMapping("/login")
    public String login(LoginDto loginDto, Model model){
        user = userDetailsService.getLogin(loginDto);
        if(user == null){
            return "invaliduser";
        }
        model.addAttribute("accounttypes", accountTypesService.getAccountTypes());
        model.addAttribute("user",user);
        return "accounttypes";
    }

    @GetMapping("/loginnow")
    public String logInNow(Model model){
        LoginDto loginDto = new LoginDto();
        model.addAttribute("login", loginDto);
        return "login";
    }

    @GetMapping("/openaccount/{accountType}")
    public String openAccountForm(@PathVariable("accountType") String accountType, Model model) {
        accountTypes = accountType;
        UserAccountDto userAccountDto = new UserAccountDto();
        model.addAttribute("userAccountDto", userAccountDto);
        return "openaccountform";
    }

    @PostMapping("/saveaccount")
    public String saveAccount(UserAccountDto userAccountDto ){
        UserAccount userAccount = new UserAccount();
        userAccount.setUserid(user.getId());
        userAccount.setAccountType(accountTypes);
        userAccount.setPanCard(userAccountDto.getPanCard());
        userAccount.setAccountNumber(accountTypes);
        userAccount.setBalance(userAccountDto.getBalance().longValue());
        userAccountService.saveAccount(userAccount);
        return "redirect:/";
    }
}