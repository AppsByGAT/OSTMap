
import org.iidp.ostmap.rest_service.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MainController.class)
@WebIntegrationTest(randomPort = true)
public class GeoTimePeriodTest {
    RestTemplate template = new TestRestTemplate();

    /**
     * Get the random chosen port number.
     */
    @Value("${local.server.port}")
    int port;

    @Test
    public void testRequest() throws Exception {
        String url = "http://localhost:" + port + "/api/geotemporalsearch?bbnorth=10.123&bbsouth=-10.456&bbeast=-30.789&bbwest=30.123&tstart=1462020290&tend=1462020291";
        ResponseEntity responseEntity = template.getForEntity(url, String.class);
        HttpStatus status = responseEntity.getStatusCode();
        HttpHeaders httpHeaders = responseEntity.getHeaders();

        assertEquals(MediaType.APPLICATION_JSON_UTF8,httpHeaders.getContentType());
        assertEquals(true,status.is2xxSuccessful());
    }

}