package admin_user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import admin_user.dto.NotificationDTO;
import admin_user.model.User;
import admin_user.service.NotificationService;
import admin_user.service.UserService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDTO notificationDTO) {
        // Get the current logged-in user
        User sender = userService.getCurrentLoggedInUser();

        if (sender == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Send the notification
        notificationService.sendNotification(notificationDTO, sender);

        return ResponseEntity.ok("Notification sent successfully");
    }

    @GetMapping("/user")
    public ResponseEntity<List<NotificationDTO>> getUserNotifications() {
        // Get the current logged-in user
        User recipient = userService.getCurrentLoggedInUser();

        if (recipient == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Get notifications for the user
        List<NotificationDTO> notifications = notificationService.getNotificationsForUser(recipient);

        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<NotificationDTO>> getAdminNotifications() {
        // Get the current logged-in admin
        User admin = userService.getCurrentLoggedInUser();

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Get notifications for the admin
        List<NotificationDTO> notifications = notificationService.getNotificationsForUser(admin);

        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/acknowledge/{notificationId}")
    public ResponseEntity<String> acknowledgeNotification(@PathVariable Long notificationId) {
        // Get the current logged-in user
        User user = userService.getCurrentLoggedInUser();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Acknowledge the notification
        notificationService.acknowledgeNotification(notificationId);

        return ResponseEntity.ok("Notification acknowledged");
    }

    // Other notification-related endpoints...
}
