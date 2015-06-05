package com.parse.broker.entities;

import java.util.List;

public class CustomersResponse {

    private Long id;

    private String name;

    private String emailId;

    private String photoUrl;

    private List<SubscriptionResponse> subscriptions;

    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<SubscriptionResponse> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionResponse> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
