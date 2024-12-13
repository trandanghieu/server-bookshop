package com.tdh.bookstore.service;

import com.tdh.bookstore.dto.OrderItemDTO;
import com.tdh.bookstore.model.*;
import com.tdh.bookstore.repository.BookRepository;
import com.tdh.bookstore.repository.OrderRepository;
import com.tdh.bookstore.repository.ShippingAddressRepository;
import com.tdh.bookstore.repository.UserRepository;
import com.tdh.bookstore.request.OrderRequestDTO;
import com.tdh.bookstore.response.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public Order createOrder(OrderRequestDTO orderRequest) {
        Order order = new Order();

        // Set user
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);

        // Set shipping address
        ShippingAddress shippingAddress = shippingAddressRepository.findById(orderRequest.getShippingAddressId())
                .orElseThrow(() -> new RuntimeException("Shipping address not found"));
        order.setShippingAddress(shippingAddress);

        // Set order items
        List<OrderItem> orderItems = orderRequest.getItems().stream().map(itemDTO -> {
            OrderItem orderItem = new OrderItem();
            Book book = bookRepository.findById(itemDTO.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            orderItem.setBook(book);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(order);
            int newStockQuantity = book.getStockQuantity() - itemDTO.getQuantity();
            if (newStockQuantity < 0) {
                throw new RuntimeException("Not enough stock for book: " + book.getTitle());
            }
            book.setStockQuantity(newStockQuantity);
            bookRepository.save(book);

            return orderItem;
        }).collect(Collectors.toList());
        order.setItems(orderItems);

        // Set other fields
        order.setStatus(orderRequest.getStatus());
        order.setDescription(orderRequest.getDescription());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setCreatedAt(LocalDateTime.now());

        // Calculate total amount
        double totalAmount = orderItems.stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);

        // Save order
        return orderRepository.save(order);
    }


    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<OrderResponseDTO> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private OrderResponseDTO convertToResponseDTO(Order order) {
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setId(order.getId());
        responseDTO.setUserId(order.getUser().getId());
        responseDTO.setItems(order.getItems().stream().map(this::convertToOrderItemDTO).collect(Collectors.toList()));
        responseDTO.setTotalAmount(order.getTotalAmount());
        responseDTO.setStatus(order.getStatus());
        responseDTO.setDescription(order.getDescription());
        responseDTO.setShippingAddressId(order.getShippingAddress().getId());
        responseDTO.setPaymentMethod(order.getPaymentMethod());
        responseDTO.setCreatedAt(order.getCreatedAt());
        return responseDTO;
    }

    private OrderItemDTO convertToOrderItemDTO(OrderItem orderItem) {
        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setBookId(orderItem.getBook().getId());
        itemDTO.setQuantity(orderItem.getQuantity());
        return itemDTO;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }

    public List<Book> getBooksByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .flatMap(order -> order.getItems().stream()
                        .map(OrderItem::getBook))
                .distinct() // To avoid duplicates
                .collect(Collectors.toList());
    }
}
