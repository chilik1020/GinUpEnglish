package com.chilik1020.grammartestsapp.model.entities;

public enum Purchases {
    ENG_A2_B2_ALL_TESTS("eng_a2_b2_all_tests");

    private final String name;

    private Purchases(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
