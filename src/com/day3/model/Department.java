package com.day3.model;

public record Department(int departmentId,String departmentName) {

    public Department {
        if (departmentId <= 0) {
            throw new IllegalArgumentException("Department ID must be greater than 0");
        }
        if (departmentName == null || departmentName.isBlank()) {
            throw new IllegalArgumentException("Department name cannot be null or blank");
        }
    }

    
}

