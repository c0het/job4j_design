package ru.job4j.serialization;

import java.util.Arrays;

public class Customer {
    private final int numberOfOrder;
    private final Contact contact;

    private final String managerName;
    private final boolean vipStatus;
    private final int[] oldOrders;


    public Customer(int numberOfOrder, Contact contact, String managerName, boolean vipStatus, int[] oldOrders) {
        this.numberOfOrder = numberOfOrder;
        this.contact = contact;
        this.managerName = managerName;
        this.vipStatus = vipStatus;
        this.oldOrders = oldOrders;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "contact=" + contact
                + ", numberOfOrder=" + numberOfOrder
                + ", managerName='" + managerName + '\''
                + ", vipStatus=" + vipStatus
                + ", oldOrders=" + Arrays.toString(oldOrders)
                + '}';
    }
}
