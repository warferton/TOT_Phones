package com.alexkirillov.telephones.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Client{
    @NotBlank
    @Size(min = 4, max = 100, message = "The client name must be at least 4 characters long (max 100 characters)")
    private String name;

    @NotBlank
    @Size(min = 10, message = "The phone number should be 10 digits long")
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$",
            message = "The phone number should follow the pattern: '123-456-7890'")
    private String phone;

    public Client(String name, String phone){
        this.name = name;
        this.phone = phone;
    }
    public Client(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return name + ", " + phone;
    }
}
