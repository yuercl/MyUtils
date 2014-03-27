package com.yuer.test;

/**
 * Created with IntelliJ IDEA.
 * User: sztp
 * Date: 14-3-24
 * Time: 下午4:09
 */
public class JustForIntelliJShortcutsTest {

    private String firstName;
    private String lastName;

    public static void main(String args[]) {
        JustForIntelliJShortcutsTest j = new JustForIntelliJShortcutsTest();
        j.setFirstName("fisrt");
        j.setLastName("last");
        j.hello();
    }

    private void hello() {
        System.out.println("FirstName:" + getFirstName() + " LastName:" + getLastName());
    }

    public JustForIntelliJShortcutsTest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public JustForIntelliJShortcutsTest(String firstName) {
        this.firstName = firstName;
    }

    public JustForIntelliJShortcutsTest() {
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
