package com.example.BinarFud;

import com.example.BinarFud.entity.*;
import com.example.BinarFud.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class Initialization {

    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init(){
        insertMerchantData();
        insertUserData();
        insertProductData();
        insertOrderData();
        insertOrderDetailData();
        log.info("Inserting Dummy Data To Database");
    }

    public void insertMerchantData(){
        Merchant merchant1 = new Merchant();
        merchant1.setMerchant_name("merchant1");
        merchant1.setMerchant_location("jakarta");
        merchant1.setOpen(true);
        merchantRepository.save(merchant1);
        Merchant merchant2 = new Merchant();
        merchant2.setMerchant_name("merchant2");
        merchant2.setMerchant_location("papua");
        merchant2.setOpen(false);
        merchantRepository.save(merchant2);
        Merchant merchant3 = new Merchant();
        merchant3.setMerchant_name("merchant3");
        merchant3.setMerchant_location("Jombang");
        merchant3.setOpen(true);
        merchantRepository.save(merchant3);
    }

    public void insertUserData(){
        User user1 = new User();
        user1.setUsername("user1");
        user1.setEmail_address("user1@yahoo.com");
        user1.setPassword("User1@");
        userRepository.save(user1);
        User user2 = new User();
        user2.setUsername("user2");
        user2.setEmail_address("user2@yahoo.com");
        user2.setPassword("User2@");
        userRepository.save(user2);
        User user3 = new User();
        user3.setUsername("user3");
        user3.setEmail_address("user3@yahoo.com");
        user3.setPassword("User3@");
        userRepository.save(user3);
    }

    public void insertProductData(){
        List<Merchant> merchantData = merchantRepository.findAll();

        Product product1 = new Product();
        product1.setProduct_name("product1");
        product1.setPrice(1000);
        product1.setAvailable(false);
        product1.setMerchant(merchantData.get(0));
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setProduct_name("product2");
        product2.setPrice(2000);
        product2.setMerchant(merchantData.get(1));
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setProduct_name("product3");
        product3.setPrice(3000);
        product3.setMerchant(merchantData.get(2));
        productRepository.save(product3);

        Product product4 = new Product();
        product4.setProduct_name("product4");
        product4.setPrice(4000);
        product4.setMerchant(merchantData.get(0));
        productRepository.save(product4);
    }

    public void insertOrderData(){
        Date date = new Date();

        List<User> userData = userRepository.findAll();

        Order order1 = new Order();
        order1.setOrder_time(date);
        order1.setDestination_address("Jakarta");
        order1.setCompleted(false);
        order1.setUser(userData.get(0));
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setOrder_time(date);
        order2.setDestination_address("Bandung");
        order2.setCompleted(true);
        order2.setUser(userData.get(1));
        orderRepository.save(order2);

        Order order3 = new Order();
        order3.setOrder_time(date);
        order3.setDestination_address("Papua");
        order3.setCompleted(false);
        order3.setUser(userData.get(2));
        orderRepository.save(order3);

        Order order4 = new Order();
        order4.setOrder_time(date);
        order4.setDestination_address("Surabaya");
        order4.setCompleted(true);
        order4.setUser(userData.get(0));
        orderRepository.save(order4);
    }

    public void insertOrderDetailData(){
        List<Order> orderData = orderRepository.findAll();
        List<Product> productData = productRepository.findAll();

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setQuantity(4);
        orderDetail1.setTotal_price(20000);
        orderDetail1.setOrder(orderData.get(0));
        orderDetail1.setProduct(productData.get(0));
        orderDetailRepository.save(orderDetail1);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setQuantity(2);
        orderDetail2.setTotal_price(30000);
        orderDetail2.setOrder(orderData.get(1));
        orderDetail2.setProduct(productData.get(1));
        orderDetailRepository.save(orderDetail2);

        OrderDetail orderDetail3 = new OrderDetail();
        orderDetail3.setQuantity(1);
        orderDetail3.setTotal_price(3000);
        orderDetail3.setOrder(orderData.get(2));
        orderDetail3.setProduct(productData.get(2));
        orderDetailRepository.save(orderDetail3);

        OrderDetail orderDetail4 = new OrderDetail();
        orderDetail4.setQuantity(6);
        orderDetail4.setTotal_price(220000);
        orderDetail4.setOrder(orderData.get(3));
        orderDetail4.setProduct(productData.get(3));
        orderDetailRepository.save(orderDetail4);
    }

}
