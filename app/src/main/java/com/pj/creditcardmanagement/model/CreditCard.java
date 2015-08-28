package com.pj.creditcardmanagement.model;

/**
 * Created by PJ on 8/24/2015.
 */
public class CreditCard {

    public static final CreditCard DUMMY = new CreditCard(0L, "Select one");

    private Long id;
    private String name;
    private String bank;
    private String number;

    public CreditCard() {
        // default constructor
    }

    public CreditCard(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return name;
    }

}