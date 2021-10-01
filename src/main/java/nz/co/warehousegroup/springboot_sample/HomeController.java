package nz.co.warehousegroup.springboot_sample;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Tag(name = "Home", description = "Home page")
@RequestMapping("")
@RestController
public class HomeController {

    @GetMapping
    @Operation(summary = "Home page greetings")
    String index(HttpServletRequest request) {
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "Greetings from Spring Boot || Running on " + hostName;
    }
}
