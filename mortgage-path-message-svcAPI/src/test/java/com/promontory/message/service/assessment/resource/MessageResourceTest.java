package com.promontory.message.service.assessment.resource;

import com.promontory.message.service.assessment.model.Digest;
import com.promontory.message.service.assessment.model.Message;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageResourceTest {


    @LocalServerPort
    private int port;

    private String baseUrl;
    private URI uri;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws URISyntaxException {
        this.baseUrl = "http://localhost:" + port + "/messages";
        this.uri = new URI(this.baseUrl);
    }

    @Test
    public void postMessage_Test(){

       Message message = new Message("testString");
       HttpEntity request = new HttpEntity<>(message);

       ResponseEntity<Digest> response = this.restTemplate.postForEntity(uri, request, Digest.class);
       Assert.assertEquals("response code should be 201", 201, response.getStatusCodeValue());
       Assert.assertNotNull("response body should not be null or empty", response.getBody().getDigest());
    }

    @Test
    public void postMessage_With_Empty_Body_Should_Return_400_Status_Test(){

        Message message = new Message();
        HttpEntity request = new HttpEntity<>(message);

        ResponseEntity<Digest> response = this.restTemplate.postForEntity(uri, request, Digest.class);
        Assert.assertEquals("response code should be 400", 400, response.getStatusCodeValue());
    }

    @Test
    public void getMessage_Test(){
        String string = "testString";
        String hexString = DigestUtils.sha256Hex(string);
        ResponseEntity<Digest> response = this.restTemplate.
                getForEntity(uri + "/" + hexString, Digest.class);

          Assert.assertEquals("response code should be 200", 200, response.getStatusCodeValue());
          Assert.assertEquals("hash digest should match", string, response.getBody().getDigest());
    }

    @Test
    public void getMessage_With_Invalid_Hash_Should_Return_Not_Found_Test(){
        String hash = "4acf0b39d9c4766709a3689f553ac01ab550545ffa4544dfc0b2cea82fba02a3";

        ResponseEntity<Digest> response = this.restTemplate.
                getForEntity(uri + "/" + hash, Digest.class);

        Assert.assertEquals("response code should be 404", 404, response.getStatusCodeValue());
    }
}
