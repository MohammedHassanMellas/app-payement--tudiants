package com.example.ensetdemospringang.repository;

import com.example.ensetdemospringang.entities.Payment;
import com.example.ensetdemospringang.entities.PaymentStatus;
import com.example.ensetdemospringang.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type)
;

}
