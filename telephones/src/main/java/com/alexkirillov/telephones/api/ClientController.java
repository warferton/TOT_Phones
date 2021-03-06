package com.alexkirillov.telephones.api;

import com.alexkirillov.telephones.model.Client;
import com.alexkirillov.telephones.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class ClientController {
    private ClientService client_service;
    @Autowired
    public ClientController(ClientService client_service) {
        super();
        this.client_service = client_service;
    }
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String getClientList(Model model){
        model.addAttribute("clients", client_service.findAll());
        model.addAttribute("client", new Client());
        return "index";
    }

    @RequestMapping(value = {"/findClient"}, method = RequestMethod.GET)
    public String findClient(@Valid @ModelAttribute("search_prefix") String prefix,
                             BindingResult bindingResult, Model model)
    {
        if(prefix.isBlank()){
            bindingResult.addError(
                    new ObjectError("search_prefix","The Search Prefix is Not Indicated"));
        }
        model.addAttribute("clients", client_service.findClients(prefix));
        model.addAttribute("client", new Client());
        return "index";
    }

    @RequestMapping(value = {"/deleteClient/{name}"}, method = RequestMethod.GET)
    public String deleteClient(@PathVariable(value = "name") String name, Model model)
    {
        client_service.deleteClient(name);
        model.addAttribute("clients", client_service.findAll());
        return getClientList(model);
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("client") Client new_client,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("clients", client_service.findAll());
            return "index";
        }
        if (client_service.addClient(new_client) == false){
            bindingResult.addError(
                    new ObjectError
                            ("client", "Client With Such Name or Number Already Exists"));
            return "index";
        }


        model.addAttribute("clients", client_service.findAll());
        return getClientList(model);
    }

}
