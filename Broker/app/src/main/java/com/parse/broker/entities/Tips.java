package com.parse.broker.entities;

import java.util.Date;

public class Tips {
    private Long id;
    private Long planId;
    private String text;
    private TipProvider provider;
    private boolean active = true;
    private Date createdDate;
}

