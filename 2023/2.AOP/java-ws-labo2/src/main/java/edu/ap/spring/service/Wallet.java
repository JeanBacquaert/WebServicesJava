package edu.ap.spring.service;

import edu.ap.spring.aop.SecurityInterceptor;
import edu.ap.spring.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.List;

@Service
@Scope("prototype")
public class Wallet {

    @Autowired
    private BlockChain bChain;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public Wallet() {
    }

    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            // initialize the key generator and generate a KeyPair
            keyGen.initialize(ecSpec, random); // 256
            KeyPair keyPair = keyGen.generateKeyPair();
            // set the public and private keys from the keyPair
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public float getBalance() {
        float total = 0;
        for (Block block : bChain.getBlocks()) {
            List<Transaction> transactions = block.getTransactions();
            for (Transaction tr : transactions) {
                //System.out.println(tr.recipient);
                if (tr.sender == this.getPublicKey()) {
                    total -= tr.value;
                } else if (tr.recipient == this.getPublicKey()) {
                    total += tr.value;
                }
            }
        }

        return total;
    }

    @SecurityInterceptor
    public Transaction sendFunds(PublicKey recipient, float value) throws Exception {

        //code moved to BlockChainAspect
		/*if(getBalance() < value) {
			System.out.println("# Not Enough funds to send transaction. Transaction Discarded.");
			throw new Exception();
		}*/

        if (this.getPublicKey() == recipient) {
            System.out.println("# You cannot transfer funds to yourself. Transaction Discarded.");
            throw new Exception();
        }
        Transaction newTransaction = new Transaction(publicKey, recipient, value);
        newTransaction.generateSignature(privateKey);

        return newTransaction;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}


