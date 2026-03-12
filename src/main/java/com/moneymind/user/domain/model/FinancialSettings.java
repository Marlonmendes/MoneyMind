package com.moneymind.user.domain.model;

import com.moneymind.shared.domain.valueObject.Currency;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Access(AccessType.FIELD)
@Data
public class FinancialSettings {

    @Column(name = "settings_id", nullable = false, updatable = false)
    private UUID settingsId;

    @Embedded
    private Currency defaultCurrency;

    @Column(name = "monthly_budget")
    private BigDecimal monthlyIncome;

    @Column(name = "alert_limit")
    private BigDecimal spendingAlertLimit;

    @Column(name = "auto_detect_subscriptions")
    private boolean subscriptionDetectionEnabled;

    @Column(name = "alert_threshold_percentage")
    private Integer alertThresholdPercentage;
    private boolean financialAlertsEnabled;

    @Column(name = "billing_cycle_day")
    private Integer billingCycleDay;

    protected FinancialSettings(){}

    public FinancialSettings(
            Currency defaultCurrency,
            BigDecimal monthlyIncome,
            BigDecimal spendingAlertLimit,
            Integer billingCycleDay
    ) {
        this.settingsId = UUID.randomUUID();
        this.defaultCurrency = Objects.requireNonNull(defaultCurrency);
        this.monthlyIncome = monthlyIncome;
        this.spendingAlertLimit = spendingAlertLimit;
        this.subscriptionDetectionEnabled = true;
        this.financialAlertsEnabled = true;
        this.billingCycleDay = billingCycleDay;
        this.alertThresholdPercentage = 80;
    }

    public static FinancialSettings defaultSettings(){
        Currency currency = new Currency("BRL");
        return new FinancialSettings(
                currency,
                new BigDecimal(1000),
                new BigDecimal(80),
                1
        );
    }

    public void changeBillingCycle(Integer day){
        if(day < 1 || day > 31){
            throw new IllegalArgumentException("Invalid billing cycle day");
        }
        this.billingCycleDay = day;
    }

    public void updateAlertThreshold(Integer percentage){
        if(percentage < 1 || percentage > 100){
            throw new IllegalArgumentException("Invalid threshold");
        }
        this.alertThresholdPercentage = percentage;
    }

    public void changeCurrency(Currency currency){
        this.defaultCurrency = Objects.requireNonNull(currency);
    }

    public void updateMonthlyIncome(BigDecimal income){
        validatePositive(income);
        this.monthlyIncome = income;
    }

    public void updateSpendingAlert(BigDecimal limit){
        validatePositive(limit);
        this.spendingAlertLimit = limit;
    }

    public void enableSubscriptionDetection(){
        this.subscriptionDetectionEnabled = true;
    }

    public void enableFinancialAlerts(){
        this.financialAlertsEnabled = true;
    }

    public void disableFinancialAlerts(){
        this.financialAlertsEnabled = false;
    }

    private void validatePositive(BigDecimal value){
        if(value != null && value.signum() < 0){
            throw new IllegalArgumentException("Value must be positive");
        }
    }
}
