package edu.ap.spring.test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpringRestTest {

    @Autowired
    private TestRestTemplate template;

    @LocalServerPort
    int randomServerPort;

    @Test
    @Order(1)
    public void test1() {
        ResponseEntity<String> response1 = template.getForEntity("http://localhost:"+ randomServerPort+ "/balance/walletA", String.class);
        ResponseEntity<String> response2 = template.getForEntity("http://localhost:"+ randomServerPort+ "/balance/walletB", String.class);

        assertTrue(response1.getBody().contains("100.0"));
        assertTrue(response2.getBody().contains("0.0"));
    }

    @Test
    @Order(2)
    public void test2() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("wallet1", "walletA");
        map.add("wallet2", "walletB");
        map.add("amount", "30.0");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        template.postForEntity("http://localhost:"+ randomServerPort+ "/transaction", request, String.class);

        ResponseEntity<String> response1 = template.getForEntity("http://localhost:"+ randomServerPort+ "/balance/walletA", String.class);
        ResponseEntity<String> response2 = template.getForEntity("http://localhost:"+ randomServerPort+ "/balance/walletB", String.class);

        assertTrue(response1.getBody().contains("70.0"));
        assertTrue(response2.getBody().contains("30.0"));
    }

    @Test
    public void test3() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("wallet1", "walletB");
        map.add("wallet2", "walletA");
        map.add("amount", "20.0");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        template.postForEntity("http://localhost:"+ randomServerPort+ "/transaction", request, String.class);

        ResponseEntity<String> response1 = template.getForEntity("http://localhost:"+ randomServerPort+ "/balance/walletA", String.class);
        ResponseEntity<String> response2 = template.getForEntity("http://localhost:"+ randomServerPort+ "/balance/walletB", String.class);

        assertTrue(response1.getBody().contains("90.0"));
        assertTrue(response2.getBody().contains("10.0"));
    }
}
