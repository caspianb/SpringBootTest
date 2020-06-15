package hello;

import hello.order.ConvertedOrderObj;
import hello.order.Order;
import hello.order.OrderRepository;

import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private ApplicationContext context;
    private OrderRepository repository;

    private final List<String> tenants = Arrays.asList("TENANT1", "TENANT2", "TENANT3");

    @BeforeEach
    public void testSetup() {
        repository = context.getBean(OrderRepository.class);
        Random random = ThreadLocalRandom.current();

        for (int i = 0; i < 1000; i++) {
            Order order = new Order();
            order.setTenant(tenants.get(random.nextInt(3)));
            order.setItemNumber(UUID.randomUUID().toString());

            ConvertedOrderObj convertedObj = new ConvertedOrderObj();
            convertedObj.setIntField(random.nextInt(10));
            convertedObj.setStringField(random.nextInt(10) + " -- test string");
            convertedObj.setDateField(Date.from(Instant.now()));

            order.setConvertedOrderObj(convertedObj);

            repository.save(order);
        }
    }

    @Test
    public void applicationTest() {
        Order order = new Order();
        order.setTenant("TENANT2");

        List<Order> orders = repository.findAll(Example.of(order));
        Assertions.assertFalse(orders.isEmpty());

        long count = repository.count(Example.of(order));
        Assertions.assertEquals(orders.size(), count);

        System.out.println(count);
    }
}
