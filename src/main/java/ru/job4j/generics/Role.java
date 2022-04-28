package ru.job4j.generics;

public class Role extends Base {

    private final String roleName;
    private final int pay;

    public Role(String id, String roleName, int pay) {
        super(id);
        this.roleName = roleName;
        this.pay = pay;
    }

    public int getPay() {
        return pay;
    }

    public String getRoleName() {
        return roleName;
    }
}
