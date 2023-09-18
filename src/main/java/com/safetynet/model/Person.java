package com.safetynet.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int zip;
    private String phone;
    private String email;

    public void setUpdate(String address,String city,int zip,String phone,String email){
        this.address=address;
        this.city=city;
        this.zip=zip;
        this.phone=phone;
        this.email=email;
    }
}
