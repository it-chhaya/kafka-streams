package dev.chanchhaya.mskafka.controller;

import dev.chanchhaya.mskafka.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getWords() {
        return ResponseEntity.ok(productService.getData());
    }

}
