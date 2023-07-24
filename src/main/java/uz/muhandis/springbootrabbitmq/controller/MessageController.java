package uz.muhandis.springbootrabbitmq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.muhandis.springbootrabbitmq.publisher.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {

    private final RabbitMQProducer producer;

    @GetMapping("publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ...");
    }
}
