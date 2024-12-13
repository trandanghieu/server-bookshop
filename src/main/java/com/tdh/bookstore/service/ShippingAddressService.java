package com.tdh.bookstore.service;

import com.tdh.bookstore.model.ShippingAddress;
import com.tdh.bookstore.repository.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingAddressService {
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    public ShippingAddress addShippingAddress(ShippingAddress address) {
        return shippingAddressRepository.save(address);
    }

    public List<ShippingAddress> getShippingAddressesByUserId(Long userId) {
        return shippingAddressRepository.findByUserId(userId);
    }

    public void deleteShippingAddress(Long id) {
        shippingAddressRepository.deleteById(id);
    }

    public List<ShippingAddress> getAllShippingAddresses() {
        return shippingAddressRepository.findAll();
    }
}
