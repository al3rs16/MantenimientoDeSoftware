package bank;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse; 

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    
    @Test
    @DisplayName("El método withdraw debe devolver true si tiene suficiente saldo")
    public void withdraw_EnoughBalance_ReturnTrue(){
        BankAccount account = new BankAccount(100);
        assertTrue(account.withdraw(50));
    }

    @Test
    @DisplayName("El método withdraw debe eliminar correctamente la cantidad retirada del saldo")
    public void withdraw_DoAWithDraw_ReturnTrue(){
        BankAccount account = new BankAccount(100);
        int balanceInit = account.getBalance();
        int n = 50;
        account.withdraw(n);
        assertEquals(balanceInit - n, account.getBalance());
    }

    @Test 
    @DisplayName("El método withdraw debe devolver false si no tiene suficiente saldo")
    public void withdraw_NotEnoughBalance_ReturnFalse(){
        BankAccount account = new BankAccount(100);
        assertFalse(account.withdraw(150));
    } 

    @Test
    @DisplayName("El método withdraw debe lanzar una excepción si la cantidad a retirar es negativa")
    public void withdraw_NegativeAmount_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        int n = -50;
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(n));
    }

    @Test
    @DisplayName("El método deposit debe devolver el balance correcto tras el depósito")
    public void deposit_PositiveAmount_ReturnTrue(){
        BankAccount account = new BankAccount(100);
        int init = account.getBalance();
        int n = 50;
        account.deposit(n);
        assertEquals(init + n, account.getBalance());
    }

    @Test
    @DisplayName("El método deposit debe lanzar una excepción si la cantidad a depositar es negativo")
    public void deposit_NegativeAmount_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        int n = -50;
        assertThrows(IllegalArgumentException.class, () -> account.deposit(n));
    }

    @Test
    @DisplayName("El método payment debe devolver el pago mensual correcto")
    public void payment_CorrectParameters_ReturnTrue(){
        BankAccount account = new BankAccount(100);
        double total_amount = 1000;
        double interest = 0.1;
        int npayments = 12;
        double expected = total_amount*(interest*Math.pow((1+interest), npayments)/(Math.pow((1+interest), npayments)-1));
        assertEquals(expected, account.payment(total_amount, interest, npayments));
    }

    @Test
    @DisplayName("El método payment debe lanzar una excepción si el parámetro total_amount es negativo")
    public void payment_NegativeAmount_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        double total_amount = -1000;
        double interest = 0.1;
        int npayments = 12;
        assertThrows(IllegalArgumentException.class, () -> account.payment(total_amount, interest, npayments));
    }

    @Test
    @DisplayName("El método payment debe lanzar una excepción si el parámetro interest es negativo")
    public void payment_NegativeInterest_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        double total_amount = 1000;
        double interest = -0.1;
        int npayments = 12;
        assertThrows(IllegalArgumentException.class, () -> account.payment(total_amount, interest, npayments));
    }

    @Test
    @DisplayName("El método payment debe lanzar una excepción si el parámetro npayments es negativo")
    public void payment_NegativeNPayments_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        double total_amount = 1000;
        double interest = 0.1;
        int npayments = -12;
        assertThrows(IllegalArgumentException.class, () -> account.payment(total_amount, interest, npayments));
    }

    @Test
    @DisplayName("El método pending debe devolver la cantidad pendiente correcta")
    public void pending_CorrectParameters_ReturnTrue(){
        BankAccount account = new BankAccount(100);
        double amount = 1000;
        double inte = 0.1;
        int npayments = 12;
        int month = 1;
        double expected = amount - (amount*(inte*Math.pow((1+inte), npayments)/(Math.pow((1+inte), npayments)-1)) - inte*amount);
        assertEquals(expected, account.pending(amount, inte, npayments, month));
    }

    @Test
    @DisplayName("El método pending debe lanzar una excepción si alguno de los parámetros es negativo")
    public void pending_NegativeParameters_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        double amount = -1000;
        double inte = -0.1;
        int npayments = -12;
        int month = -1;
        assertThrows(IllegalArgumentException.class, () -> account.pending(amount, inte, npayments, month));
    }

    @Test
    @DisplayName("El método pending debe lanzar una excepción si el parámetro amount es negativo")
    public void pending_NegativeAmount_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        double amount = -1000;
        double inte = 0.1;
        int npayments = 12;
        int month = 1;
        assertThrows(IllegalArgumentException.class, () -> account.pending(amount, inte, npayments, month));
    }

    @Test
    @DisplayName("El método pending debe lanzar una excepción si el parámetro interest es negativo")
    public void pending_NegativeInterest_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        double amount = 1000;
        double inte = -0.1;
        int npayments = 12;
        int month = 1;
        assertThrows(IllegalArgumentException.class, () -> account.pending(amount, inte, npayments, month));
    }

    @Test
    @DisplayName("El método pending debe lanzar una excepción si el parámetro npayments es negativo")
    public void pending_NegativeNPayments_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        double amount = 1000;
        double inte = 0.1;
        int npayments = -12;
        int month = 1;
        assertThrows(IllegalArgumentException.class, () -> account.pending(amount, inte, npayments, month));
    }

    @Test
    @DisplayName("El método pending debe lanzar una excepción si el parámetro month es negativo")
    public void pending_NegativeMonths_ThrowIllegalArgumentException(){
        BankAccount account = new BankAccount(100);
        double amount = 1000;
        double inte = 0.1;
        int npayments = 12;
        int month = -1;
        assertThrows(IllegalArgumentException.class, () -> account.pending(amount, inte, npayments, month));
    }
    
}
