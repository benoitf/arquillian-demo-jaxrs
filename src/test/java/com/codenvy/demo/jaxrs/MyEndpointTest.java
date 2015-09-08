package com.codenvy.demo.jaxrs;

import com.jayway.restassured.response.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunAsClient
@RunWith(Arquillian.class)
public class MyEndpointTest {

    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "jaxrs-test.war")
                         .addClass(MyEndpoint.class)
                         .addClass(ApplicationConfig.class);
    }

    @ArquillianResource URL endpointURL;

    @Test
    public void testVersion() {
        final String url = endpointURL + "myapp/service/version";
        Response response = get(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.asString(), is(MyEndpoint.VERSION));
    }



    @Test
    public void testEcho() {

        final String url = endpointURL + "myapp/service/echo";
        Response response = given().body("florent").post(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.asString(), is("hello florent"));
    }
}