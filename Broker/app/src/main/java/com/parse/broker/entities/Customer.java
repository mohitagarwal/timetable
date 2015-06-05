package com.parse.broker.entities;

import java.util.List;

public class Customer {
    private Long id;
    private String name;
    private String emailId;
    private String password;
    private String photoUrl;
    private List<Subscription> subscriptions;
    private boolean active;
}

