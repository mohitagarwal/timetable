package com.parse.broker.entities;

import java.util.Date;

public class Subscription {

    Long id;

    private Customer customer;

    private Plan plan;

    private Long providerId;

    private Date expDate;

    private boolean active;

}