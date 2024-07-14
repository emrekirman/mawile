package com.emrekirman.mawile.bankAccount;

import com.emrekirman.mawile.bankAccount.service.impl.BankAccountNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountNumberGeneratorTests {

    @Autowired
    BankAccountNumberGenerator bankAccountNumberGenerator;


    @Test
    void generateTest() {

        String generate = bankAccountNumberGenerator.generate();

        System.out.println("generated value : " + generate);
        Assertions.assertNotNull(generate);
    }
}
