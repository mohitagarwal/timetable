package com.parse.broker.entities;

public class PlanResponse {

    private Long id;

    private String planName;

    private ContractType contractType;

    private ExchangeType exchange;

    private Long recommendedPrice;

    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public ExchangeType getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeType exchange) {
        this.exchange = exchange;
    }

    public Long getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(Long recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
