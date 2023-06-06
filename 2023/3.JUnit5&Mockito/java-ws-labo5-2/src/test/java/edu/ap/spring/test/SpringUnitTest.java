package edu.ap.spring.test;

import edu.ap.spring.service.Block;
import edu.ap.spring.service.BlockChain;
import edu.ap.spring.service.Wallet;
import edu.ap.spring.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpringUnitTest {

    @Autowired
    private BlockChain bChain;

    @Autowired
    private Wallet coinbase;
    @Autowired
    private Wallet walletA;
    @Autowired
    private Wallet walletB;

    private Wallet walletC;

    @BeforeEach
    public void init() {
        bChain.setSecurity();
        coinbase.generateKeyPair();
        walletA.generateKeyPair();
        walletB.generateKeyPair();

        walletC = Mockito.mock(Wallet.class);

        // create genesis transaction, which sends 100 coins to walletA:
        Transaction genesisTransaction = new Transaction(coinbase.getPublicKey(), walletA.getPublicKey(), 100f);
        genesisTransaction.generateSignature(coinbase.getPrivateKey());     // manually sign the genesis transaction
        genesisTransaction.transactionId = "0"; // manually set the transaction id

        // creating and mining Genesis block
        Block genesisBlock = new Block();
        genesisBlock.setPreviousHash("0");
        genesisBlock.addTransaction(genesisTransaction, bChain);
        bChain.addBlock(genesisBlock);
    }

    @Test
    public void test1() {
        Block block = new Block();
        block.setPreviousHash(bChain.getLastHash());

        try {
            block.addTransaction(walletA.sendFunds(walletB.getPublicKey(), 40f), bChain);
            block.addTransaction(walletA.sendFunds(walletB.getPublicKey(), 10f), bChain);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bChain.addBlock(block);

        assertEquals(50f, walletA.getBalance(), 0);
        assertEquals(50f, walletB.getBalance(), 0);
    }

    @Test
    public void test2() {
        Block block1 = new Block();
        block1.setPreviousHash(bChain.getLastHash());

        try {
            block1.addTransaction(walletA.sendFunds(walletB.getPublicKey(), 80f), bChain);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bChain.addBlock(block1);

        Block block2 = new Block();
        block2.setPreviousHash(bChain.getLastHash());

        try {
            block2.addTransaction(walletB.sendFunds(walletA.getPublicKey(), 40f), bChain);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bChain.addBlock(block2);

        assertEquals(60f, walletA.getBalance(), 0);
        assertEquals(40f, walletB.getBalance(), 0);
    }

    @Test
    public void test3() {

        //configure mocking behaviour
        Mockito.when(walletC.getBalance()).thenReturn(50f);

        assertEquals(50f, walletC.getBalance(), 0);
    }
}
