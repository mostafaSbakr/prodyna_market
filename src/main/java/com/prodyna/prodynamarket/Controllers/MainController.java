package com.prodyna.prodynamarket.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(path = "/main")
    public String redirectMainPage() {
        return "main";
    }

    @GetMapping(path = "/sign-in")
    public String redirectSignInPage() {
        return "sign-in";
    }

    @GetMapping(path = "/admin-sign-in")
    public String redirectSignInAdminPage() {
        return "admin-sign-in";
    }

    @GetMapping(path = "/admin")
    public String redirectAdminPage() {
        return "admin";
    }

    @GetMapping(path = "/sign-up")
    public String redirectSignUpPage() {
        return "sign-up";
    }

    @GetMapping(path = "/accounts")
    public String redirectAccountsPage() {
        return "accounts";
    }

    @GetMapping(path = "/products")
    public String redirectProductsPage()  {
        return "products";
    }

    @GetMapping(path = "/user-market")
    public String redirectUserMarketPage() {
        return "user-market";
    }

    @GetMapping(path = "/user-orders")
    public String redirectUserOrdersPage() { return "user-orders";  }

    @GetMapping(path = "/user-profile")
    public String redirectUserProfilePage() { return "user-profile"; }
}
