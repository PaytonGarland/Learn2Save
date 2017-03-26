package com.mcbank.nessie;
import java.awt.List;
import java.util.ArrayList;

public class Customer
{
    private String _id;
    private String first_name;
    private String last_name;
    private Address address;

    public Customer()
    {
        address = new Address();
    }

    public String getId()
    {
        return _id;
    }
    public void setId(String id)
    {
        this._id = id;
    }
    public String getFirstName()
    {
        return first_name;
    }
    public void setFirstName(String firstName)
    {
        this.first_name = firstName;
    }
    public String getLastName()
    {
        return last_name;
    }
    public void setLastName(String lastName)
    {
        this.last_name = lastName;
    }
    public Address getAddress()
    {
        return this.address;
    }
    public void setAddress(Address address)
    {
        this.address = address;
    }
}
