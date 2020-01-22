package com.cdk.global.shopping.cart.model;

import java.util.Objects;

public class DiscountSlab implements Comparable<DiscountSlab>{

    private double low;
    private double high;
    private double discount;

    public DiscountSlab(double low, double high, double discount) {
        this.low = low;
        this.high = high;
        this.discount = discount;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public int compareTo(DiscountSlab other) {
        return Double.compare(this.low,other.getLow());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountSlab)) return false;
        DiscountSlab slab = (DiscountSlab) o;
        return Double.compare(slab.getLow(), getLow()) == 0 &&
                Double.compare(slab.getHigh(), getHigh()) == 0 &&
                Double.compare(slab.getDiscount(), getDiscount()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLow(), getHigh(), getDiscount());
    }
}
