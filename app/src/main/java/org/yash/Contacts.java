package org.yash;

public class Contacts {
    private String name, email;

    public Contacts(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
}
