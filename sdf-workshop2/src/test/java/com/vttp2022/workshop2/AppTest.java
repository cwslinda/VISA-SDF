package com.vttp2022.workshop2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.vttp2022.workshop2.BankAccount;
/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue(){
        assertTrue( true );
    }

    @Test
    public void testBankAccount(){
        BankAccount bkAcc = new BankAccount("CWS Bank account");
        bkAcc.deposit("1000");
        float finalBalance = bkAcc.withdraw("500");
        assertEquals( 500, finalBalance,.1 );
    }

    // @Test 
    // public void testFixedDepositAccountChangeInterestAndDuration(){
    //     try{
            
    //         FixedDepositAccount fdAcc = new FixedDepositAccount("FD Acc", 1000);
    //         System.out.println("1. Fixed Deposit Acc balance" + fdAcc.getBalance());
    //         fdAcc.setDurationInterest(4, 12);
    //         System.out.println("2. Fixed Deposit Acc balance" + fdAcc.getBalance());
    //         fdAcc.setDurationInterest(5, 6);

    //     } catch(IllegalArgumentException e){
    //         assertTrue("Only can set duration and interest once.".contains(e.getMessage()));
    //     }
    //}

}
