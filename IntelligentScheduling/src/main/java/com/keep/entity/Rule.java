package com.keep.entity;

public class Rule {
    private String id;
    private String fixedRule;
    private String selfRule;

    public Rule() {}
    public Rule(String id, String fixedRule, String selfRule) {
        this.id = id;
        this.fixedRule = fixedRule;
        this.selfRule = selfRule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFixedRule() {
        return fixedRule;
    }

    public void setFixedRule(String fixedRule) {
        this.fixedRule = fixedRule;
    }

    public String getSelfRule() {
        return selfRule;
    }

    public void setSelfRule(String selfRule) {
        this.selfRule = selfRule;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id='" + id + '\'' +
                ", fixedRule='" + fixedRule + '\'' +
                ", selfRule='" + selfRule + '\'' +
                '}';
    }
}
