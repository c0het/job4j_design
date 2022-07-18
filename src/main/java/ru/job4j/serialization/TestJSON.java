package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestJSON {
    public static void main(String[] args) {
        final Customer customer = new Customer(4444, new Contact(111,
                "444-444-444"), "Stanislav", false, new int[] {1, 2});


        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(customer);
        System.out.println(json);

        final Customer customer1 = gson.fromJson(json, Customer.class);
        System.out.println(customer1);
    };
}
