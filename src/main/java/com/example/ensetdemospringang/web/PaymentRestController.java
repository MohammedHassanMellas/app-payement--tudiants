package com.example.ensetdemospringang.web;

import com.example.ensetdemospringang.entities.Payment;
import com.example.ensetdemospringang.entities.PaymentStatus;
import com.example.ensetdemospringang.entities.PaymentType;
import com.example.ensetdemospringang.entities.Student;
import com.example.ensetdemospringang.repository.PaymentRepository;
import com.example.ensetdemospringang.repository.StudentRepository;
import com.example.ensetdemospringang.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    public PaymentRestController(StudentRepository studentRepository
            , PaymentRepository paymentRepository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/payments")
    public List<Payment> allPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> PaymentsByStudent(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payments/byStatus")
    public List<Payment> PaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@PathVariable Long id, @RequestParam PaymentStatus status) {
        return paymentService.updatePaymentStatus(id, status);
    }

    @GetMapping(path = "/payments/byType")
    public List<Payment> PaymentsByType(PaymentType type) {
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }

    @GetMapping(path = "/students")
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}")
    public Student getStudentByCode(@PathVariable String code) {
        return studentRepository.findByCode(code);
    }

    @GetMapping(path = "/studentsByProgramId")
    public List<Student> getStudentsByProgramId(@RequestParam String programId) {
        return studentRepository.findByProgramId(programId);
    }

    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double amount,
                                  PaymentType type, String studentCode) throws IOException {

        return this.paymentService.savePayment(file,date, amount,type ,studentCode);


}
@GetMapping(path = "/paymentFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        return paymentService.getPaymentFile(paymentId);
    }


}
