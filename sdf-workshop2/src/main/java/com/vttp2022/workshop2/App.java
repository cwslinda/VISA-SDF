package com.vttp2022.workshop2;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "My ATM Machine - POSSBank" );
        BankAccount bkAcc = new BankAccount("CWS Bank account");
        bkAcc.deposit("1000");
        System.out.println("My new account balance >\n" + bkAcc.getBalance());
        // bkAcc.withdraw("1000");
        // System.out.println("My new account balance >\n" + bkAcc.getBalance());
        System.out.println("My new account balance >" +bkAcc.withdraw("500"));

        FixedDepositAccount fdAcc = new FixedDepositAccount("cws fd acc", 1000);
        System.out.println("fixed  deposit acc balanace\n" +  fdAcc.getBalance());
        



    }
}
