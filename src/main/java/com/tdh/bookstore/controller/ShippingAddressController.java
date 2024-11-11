package com.tdh.bookstore.controller;

import com.tdh.bookstore.model.ShippingAddress;
import com.tdh.bookstore.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipping-addresses")
public class ShippingAddressController {
    @Autowired
    private ShippingAddressService shippingAddressService;

    @PostMapping
    public ResponseEntity<ShippingAddress> addShippingAddress(@RequestBody ShippingAddress address) {
        return ResponseEntity.ok(shippingAddressService.addShippingAddress(address));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ShippingAddress>> getShippingAddressesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(shippingAddressService.getShippingAddressesByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable Long id) {
        shippingAddressService.deleteShippingAddress(id);
        return ResponseEntity.noContent().build();
    }
}
