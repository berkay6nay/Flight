package com.example.berkay.Flight.repository;

import com.example.berkay.Flight.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
