package admin_user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import admin_user.dto.NotificationDTO;
import admin_user.model.User;

@Service
public interface NotificationService {
    List<NotificationDTO> getNotificationsForUser(User user);
    void sendNotification(NotificationDTO notificationDTO, User recipient);
    void acknowledgeNotification(Long notificationId);
}
