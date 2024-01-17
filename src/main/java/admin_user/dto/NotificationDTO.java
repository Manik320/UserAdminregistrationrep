package admin_user.dto;

import java.time.LocalDateTime;

public class NotificationDTO {
    private String sender;
    private LocalDateTime timestamp;
    private String content;
	public NotificationDTO(Long id, String content2, LocalDateTime timestamp2, boolean acknowledged) {
		// TODO Auto-generated constructor stub
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

    // getters and setters
    
}
