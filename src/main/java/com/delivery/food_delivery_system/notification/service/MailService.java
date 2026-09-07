package com.delivery.food_delivery_system.notification.service;

import com.delivery.food_delivery_system.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author Gourav
 **/
@Slf4j
@Service
public class MailService {

    //--- Dependency
    private final JavaMailSender javaMailSender;


    public MailService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public void sendMail(String to, String subject, Order order){
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();


        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Order ID: ").append(order.getId()).append("\n");
        stringBuilder.append("Customer Name: ").append(order.getCustomerName()).append("\n");
        stringBuilder.append("Restaurant Name: ").append(order.getRestaurantName()).append("\n");
        stringBuilder.append("Order Status: ").append(order.getStatus()).append("\n");
        stringBuilder.append("Order Time: ").append(order.getCreatedAt()).append("\n\n");

        stringBuilder.append("--- ITEMS PURCHASED ---\n");

        order.getOrderItemList().forEach(item-> stringBuilder
                .append(item.getName()).append("   ")
                .append(item.getQuantity())
                .append("   $ ").append(item.getPrice()).append("\n"));

        stringBuilder.append("--- Grand total --- $").append(order.getAmount());

        String body= stringBuilder.toString();

        try {

            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            log.error("Error Sending the mail",e);
        }


    }

}
