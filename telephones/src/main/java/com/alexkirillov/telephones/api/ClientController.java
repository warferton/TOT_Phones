package com.alexkirillov.telephones.api;

import com.alexkirillov.telephones.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
    private ClientService client_service;
//
//    @Value("${errorMessage}")
//    private  String errorMsg;

    public ClientController(ClientService client_service) {
        super();
        this.client_service = client_service;
    }

    @GetMapping(value = {"/index","/"})
    public String index(Model model){
        model.addAttribute("clients",client_service.findClients(""));
        return "index";
    }

    @GetMapping(value = {"/results"})
    public String findClient(@RequestParam(value = "client-name", required = false,
                                defaultValue = "none") String client_name
                                ,Model model){
        model.addAttribute("clients", client_service.findClients(client_name));
        return "index";

    }

}
