package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class RoleStoreTest {

    @Test
    public void testAdd() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Anton", 25000));
        roleStore.add(new Role("2", "Igor", 35000));
        Role rsl = roleStore.findById("2");
        assertThat(rsl.getRoleName(), is("Igor"));
        assertThat(rsl.getPay(), is(35000));
    }

    @Test
    public void testAddWhenIdDuplicate() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Anton", 25000));
        roleStore.add(new Role("1", "Igor", 35000));
        Role rsl = roleStore.findById("1");
        assertThat(rsl.getRoleName(), is("Anton"));
        assertThat(rsl.getPay(), is(25000));
    }

    @Test
    public void testReplace() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Anton", 25000));
        roleStore.replace("1", new Role("1", "Igor", 35000));
        Role rsl = roleStore.findById("1");
        assertThat(rsl.getRoleName(), is("Igor"));
        assertThat(rsl.getPay(), is(35000));
    }

    @Test
    public void testDelete() {
    RoleStore roleStore = new RoleStore();
    roleStore.add(new Role("1", "Anton", 25000));
    roleStore.delete("1");
    Role rsl = roleStore.findById("1");
    assertNull(rsl);
}

}