//package ma.chatapp.controller;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//
//@Controller
//public class WebSocketController {
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @MessageMapping("/sendMessage") // Client will send messages to /app/sendMessage
//    @SendTo("/topic/messages")      // Broadcasts to clients subscribed to /topic/messages
//    public String processMessage(String message) {
//        return message; // Just echoing the message for simplicity
//    }
//
//    // Optionally, you can push messages to clients from another service
//    public void sendMessageToClients(String message) {
//        messagingTemplate.convertAndSend("/topic/messages", message);
//    }
//}
