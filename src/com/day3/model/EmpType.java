package com.day3.model;

public enum EmpType {
    FULLTIME,
    PARTTIME,
    INTERN;

    @Override 
    public String toString() {
        return switch (this) {
            case FULLTIME->"Full-time Employee";
            case PARTTIME->"Part-time Employee";
            case INTERN->"Intern";
            
        };
        
    }
    
}

