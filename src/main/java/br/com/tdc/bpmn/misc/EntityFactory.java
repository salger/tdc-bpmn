package br.com.tdc.bpmn.misc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import br.com.tdc.bpmn.domain.Address;
import br.com.tdc.bpmn.domain.City;
import br.com.tdc.bpmn.domain.Customer;
import br.com.tdc.bpmn.domain.Item;
import br.com.tdc.bpmn.domain.Name;
import br.com.tdc.bpmn.domain.Order;
import br.com.tdc.bpmn.domain.OrderItem;

public class EntityFactory {

    private static City[] cities = { new City("Uberlândia", "MG"), new City("Uberaba", "MG"),
            new City("Araguari", "MG") };

    private static Address[] addresses = { new Address(cities[0], "Rua Suécia", 770),
            new Address(cities[0], "Rua Prof. Maria Alves Castilho", 1026),
            new Address(cities[1], "Rua Arlindo Cruz", 35), new Address(cities[2], "Av. Central", 412) };

    private static Name[] names = { new Name("Jerry Adriane", "Gonçalves"), new Name("José Gonçalves", "Matias"),
            new Name("Lucas", "Silva"), new Name("Aurélio", "Miguel"), new Name("Alberto", "Cunha") };

    private static Customer[] customers = { new Customer(1, names[0]), new Customer(2, names[1]),
            new Customer(3, names[2]), new Customer(4, names[3]), new Customer(5, names[4]) };

    private static Item[] items = { new Item(1, "Parafuso"), new Item(1, "Porca"), new Item(1, "Arruela"),
            new Item(1, "Prego"), new Item(1, "Rebite") };

    private static OrderItem[] orderItems1 = { new OrderItem(items[0], new BigDecimal(12)),
            new OrderItem(items[1], new BigDecimal(126)), new OrderItem(items[2], new BigDecimal(28)),
            new OrderItem(items[3], new BigDecimal(67)), new OrderItem(items[4], new BigDecimal(49)) };

    private static OrderItem[] orderItems2 = { new OrderItem(items[1], new BigDecimal(17)),
            new OrderItem(items[3], new BigDecimal(2)), new OrderItem(items[4], new BigDecimal(13)) };

    private static OrderItem[] orderItems3 = { new OrderItem(items[2], new BigDecimal(99)),
            new OrderItem(items[4], new BigDecimal(25)) };

    private static OrderItem[] orderItems4 = { new OrderItem(items[1], new BigDecimal(8)),
            new OrderItem(items[3], new BigDecimal(17)) };

    private static OrderItem[] orderItems5 = { new OrderItem(items[0], new BigDecimal(32)) };

    private static Order[] orders = { new Order(customers[0], addresses[1], Arrays.asList(orderItems1)),
            new Order(customers[1], addresses[0], Arrays.asList(orderItems2)),
            new Order(customers[2], addresses[2], Arrays.asList(orderItems3)),
            new Order(customers[3], addresses[3], Arrays.asList(orderItems4)),
            new Order(customers[4], addresses[3], Arrays.asList(orderItems5)) };

    public static City getCity() {
        return cities[0];
    }

    public static List<City> getCities() {
        return Arrays.asList(cities);
    }

    public static Address getAddress() {
        return addresses[0];
    }

    public static List<Address> getAddresses() {
        return Arrays.asList(addresses);
    }

    public static Name getName() {
        return names[0];
    }

    public static List<Name> getNames() {
        return Arrays.asList(names);
    }

    public static Customer getCustomer() {
        return customers[0];
    }

    public static List<Customer> getCustomers() {
        return Arrays.asList(customers);
    }

    public static Item getItem() {
        return items[0];
    }

    public static List<Item> getItems() {
        return Arrays.asList(items);
    }

    public static Order getOrder() {
        return orders[0];
    }

    public static List<Order> getOrders() {
        return Arrays.asList(orders);
    }

}
