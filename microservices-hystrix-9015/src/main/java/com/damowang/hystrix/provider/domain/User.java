package com.damowang.hystrix.provider.domain;

import java.math.BigDecimal;

/**
 * 描述:
 * 展示hystrix的用法
 *
 * @author sculi
 * @create 2020-05-10 23:35
 */
public class User {
    private String username;
    private String password;
    private BigDecimal balance;
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
