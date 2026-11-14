package com.day3.model;

public record Designation(int designationId, String designationName) {

    public Designation {
        if (designationId <= 0) {
            throw new IllegalArgumentException("Designation ID must be greater than 0");
        }
        if (designationName == null || designationName.isBlank()) {
            throw new IllegalArgumentException("Designation name cannot be null or blank");
        }
    }
    
}

