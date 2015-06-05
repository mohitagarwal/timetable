package com.parse.broker.entities;

import java.io.Serializable;
import java.util.List;

public class GetAllProviderCustomers extends Response{

    List<CustomersResponse> customers;

    public List<CustomersResponse> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomersResponse> customers) {
        this.customers = customers;
    }
}
