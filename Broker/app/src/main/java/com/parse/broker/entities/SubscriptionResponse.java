package com.parse.broker.entities;

import java.util.Date;

public class SubscriptionResponse {

    Long id;

    private PlanResponse plan;

    private Long providerId;

    private Date expDate;

    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanResponse getPlan() {
        return plan;
    }

    public void setPlan(PlanResponse plan) {
        this.plan = plan;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
