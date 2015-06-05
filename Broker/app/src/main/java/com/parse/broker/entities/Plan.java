package com.parse.broker.entities;

import java.util.List;

public class Plan {

    private Long id;

    private TipProvider tipProvider;

    private List<Subscription> subscriptions;

    private String planName;

    private ContractType contractType;

    private ExchangeType exchange;

    private Long recommendedPrice;

    private boolean active;
}
