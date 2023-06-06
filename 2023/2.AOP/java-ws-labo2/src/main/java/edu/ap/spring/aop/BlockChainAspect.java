package edu.ap.spring.aop;

import edu.ap.spring.service.Wallet;
import edu.ap.spring.transaction.Transaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BlockChainAspect {

    @Around("@annotation(edu.ap.spring.aop.SecurityInterceptor) && execution(public * sendFunds(..)) ")
    public Transaction checkBalance(ProceedingJoinPoint joinPoint) throws Throwable {

        Transaction result = null;
        Wallet wallet = (Wallet) joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        float value = (float) args[1];
        float balance = wallet.getBalance();

        if (balance < value) {
            System.out.println("# Not Enough funds to send transaction. Transaction Discarded.");
        } else {
            result = (Transaction) joinPoint.proceed();
        }

        return result;
    }
}

