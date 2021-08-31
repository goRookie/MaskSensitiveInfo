package com.rick.log4j.pojo;

/**
 * @author rick
 * E-mail:sophie_zelmani@163.com
 * @version 2021/8/25 16:56
 */
public class Customer implements java.io.Serializable {
    private String name;
    private int age;
    private String creditCardNo;
    private String creditCardPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCreditCardPassword() {
        return creditCardPassword;
    }

    public void setCreditCardPassword(String creditCardPassword) {
        this.creditCardPassword = creditCardPassword;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", creditCardNo='" + creditCardNo + '\'' +
                ", creditCardPassword='" + creditCardPassword + '\'' +
                '}';
    }
}
