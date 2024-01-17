package admin_user.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import admin_user.dto.NotificationDTO;
import admin_user.model.Notification;
import admin_user.model.User;
import admin_user.repositories.NotificationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository; // Assuming you have a NotificationRepository

    @Override
    public List<NotificationDTO> getNotificationsForUser(User user) {
        // Assuming the Notification entity has a field 'recipient' representing the user who will receive the notification
        List<Notification> userNotifications = notificationRepository.findByRecipient(user);

        // Convert Notification entities to NotificationDTOs
        return userNotifications.stream()
                .map(notification -> new NotificationDTO(notification.getId(), notification.getContent(), notification.getTimestamp(), notification.isAcknowledged()))
                .collect(Collectors.toList());
    }

    @Override
    public void sendNotification(NotificationDTO notificationDTO, User recipient) {
        // Convert NotificationDTO to Notification entity
        Notification notification = new Notification();
        notification.setContent(notificationDTO.getContent());
        notification.setTimestamp(notificationDTO.getTimestamp());
        notification.setRecipient(recipient);
        notification.setAcknowledged(false);

        // Save the notification to the database
        notificationRepository.save(notification);
    }

    @Override
    public void acknowledgeNotification(Long notificationId) {
        // Retrieve the notification from the database
        Notification notification = notificationRepository.findById(notificationId).orElse(null);

        // Update the 'acknowledged' flag
        if (notification != null) {
            notification.setAcknowledged(true);
            notificationRepository.save(notification);
        }
    }
}
