package io.codelex.urlshortener.requests;

public class Expiration {
        private final String unit;
        private final int amount;

    public Expiration(String unit, int expiration) {
        this.unit = unit;
        this.amount = expiration;
    }

    public String getUnit() {
        return unit;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Expiration{" + "unit='" + unit + '\'' + ", expiration=" + amount + '}';
    }
}
