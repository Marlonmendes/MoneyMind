package com.moneymind.shared.domain.valueObject;

import lombok.Getter;

import java.util.Objects;
import java.util.Set;

public final class Currency {

    private static final Set<String> SUPPORTED = Set.of("BRL", "USD", "EUR");

    @Getter
    private final String code;

    public Currency(String code) {
        this.code = code;
    }

    public static Currency of(String code){
        Objects.requireNonNull(code, "Currency cannot be Null");

        String normalized = code.toUpperCase();

        if(!SUPPORTED.contains(normalized)){
            throw new IllegalArgumentException(
                    "Unsupported currency: " + code
            );
        }

        return new Currency(normalized);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Currency that)) return false;
        return code.equals(that.code);
    }

    @Override
    public int hashCode(){
        return Objects.hash(code);
    }

    @Override
    public String toString(){
        return code;
    }
}
