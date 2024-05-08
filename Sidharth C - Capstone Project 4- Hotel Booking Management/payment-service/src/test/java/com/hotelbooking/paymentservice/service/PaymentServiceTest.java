package com.hotelbooking.paymentservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelbooking.paymentservice.dao.PaymentDao;
import com.hotelbooking.paymentservice.entity.Payments;
import com.hotelbooking.paymentservice.exception.PaymentFailedException;
import com.hotelbooking.paymentservice.exception.ResourceNotFoundException;
import com.hotelbooking.paymentservice.model.BookingResponse;
import com.hotelbooking.paymentservice.model.PaymentResponse;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class PaymentServiceTest {
	
	@Mock
    private PaymentDao paymentDao;

    @Mock
    private BookingServiceConsumer bookingService;

    @InjectMocks
    private PaymentServiceImpl paymentService;
    
    @Test
    public void testMakePayment() {
        Payments payment = new Payments();
        payment.setBookingId(1);
        payment.setPAmount(100.0);

        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setAmount(100.0);

        when(bookingService.getBookingDetailsById(1)).thenReturn(bookingResponse);
        when(paymentDao.existsByBookingId(1)).thenReturn(false);
        when(paymentDao.save(payment)).thenReturn(payment);

        PaymentResponse response = paymentService.makePayment(payment);

        assertEquals("Payment Successfull", response.getMessage());
        assertEquals(bookingResponse, response.getBookingResponse());
    }
    
    @Test
    public void testMakePaymentPaymentAlreadyExistException() {
        Payments payment = new Payments();
        payment.setBookingId(1);

        when(paymentDao.existsByBookingId(1)).thenReturn(true);

        assertThrows(PaymentFailedException.class, () -> {
            paymentService.makePayment(payment);
        });
    }

    @Test
    public void testViewPaymentsById() {
        Payments payment = new Payments();
        payment.setPaymentId(1);

        when(paymentDao.findById(1)).thenReturn(Optional.of(payment));

        Payments result = paymentService.viewPaymentsById(1);

        assertEquals(payment, result);
    }

    @Test
    public void testViewPaymentsByIdPaymentNotFoundException() {
        when(paymentDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            paymentService.viewPaymentsById(1);
        });
    }

    @Test
    public void testViewAllPayments() {
        List<Payments> payments = new ArrayList<>();
        payments.add(new Payments());
        payments.add(new Payments());

        when(paymentDao.findAll()).thenReturn(payments);

        List<Payments> result = paymentService.viewAllPayments();

        assertEquals(payments.size(), result.size());
    }
    
    @Test
    public void testMakePaymentAmountMismatchException() {
        Payments payment = new Payments();
        payment.setBookingId(1);
        payment.setPAmount(100.0);

        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setAmount(200.0);

        when(bookingService.getBookingDetailsById(1)).thenReturn(bookingResponse);

        assertThrows(PaymentFailedException.class, () -> {
            paymentService.makePayment(payment);
        });
    }

}
