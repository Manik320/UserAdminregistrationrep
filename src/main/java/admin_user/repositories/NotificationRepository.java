package admin_user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import admin_user.model.Notification;
import admin_user.model.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientAndAcknowledgedOrderByTimestampDesc(User recipient, boolean acknowledged);

	List<Notification> findByRecipient(User user);
}
