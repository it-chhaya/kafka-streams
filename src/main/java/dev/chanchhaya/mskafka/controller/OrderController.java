package dev.chanchhaya.mskafka.controller;

import dev.chanchhaya.mskafka.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Object> getOrders() {
        return orderService.getOrders();
    }

}
