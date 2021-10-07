package ca.mikegabelmann.demo.rest.controller;

import ca.mikegabelmann.demo.rest.persistence.model.Address;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/**
 *
 * @author mgabe
 */
@RestController
public class HelloController {
    public static final String PATH_HELLO_GET = "/hello";
    public static final String PATH_HELLO_BY_NAME_GET = "/helloName";
    public static final String PATH_GET_ADDRESS = "/address/{addressId}";

    private static final Logger LOG = LogManager.getLogger(HelloController.class);


    @GetMapping(path=HelloController.PATH_HELLO_GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping(path=HelloController.PATH_HELLO_BY_NAME_GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> helloName(@RequestParam("name") final String name) {
        String message;

        if (name == null || "".equals(name)) {
            message = "Hello?!?";

        } else {
            message = String.format("Hello %s", name);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(path=HelloController.PATH_GET_ADDRESS, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> getAddress(@PathVariable("addressId") long addressId) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("addressId={}", addressId);
        }

        if (addressId == 2) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");

        } else {
            Address address = new Address("#1 Java Way", "Victoria", "V9Z0Y7");
            return new ResponseEntity<>(address, HttpStatus.OK);
        }
    }

}
