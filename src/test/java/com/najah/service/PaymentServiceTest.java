package com.najah.service;
import com.najah.domain.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {
    @Test
    public void testSuccessProcessCashPayment() {
        OrderService orderService = new OrderService();
        Order order = new Order("0-04", null, null, java.util.Collections.emptyList(), "Nablus");
        PaymentService cashPayment = new CashPaymentService();
        orderService.payForOrder(order, 50.0, cashPayment);

        assertTrue(order.isPaid());
    }
        @Test
        public void testInvalidAmountProcessCashPayment(){
            PaymentService cashPayment = new CashPaymentService();
            try{
                cashPayment.processPayment(-13.0);
                fail("Test Failed: The System should have thrown an exception but it didn't");

            }catch(IllegalArgumentException e){
                assertTrue(e.getMessage().contains("Invalid payment amount"));

            }
        }
    @Test
    public void testProcessPayment() {
        OrderService orderService = new OrderService();
        Order order = new Order("0-05", null, null, java.util.Collections.emptyList(), "Nablus");
        PaymentService mockPayment = Mockito.mock(PaymentService.class);
        when(mockPayment.processPayment(100.5)).thenReturn(true);
        orderService.payForOrder(order, 100.5, mockPayment);

        assertTrue(order.isPaid());
        verify(mockPayment, times(1)).processPayment(100.5);
    }


    }
