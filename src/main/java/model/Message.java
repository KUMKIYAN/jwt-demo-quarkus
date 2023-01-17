package model;

public class Message {
	
	public String content;

	public Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message{" +
				"content='" + content + '\'' +
				'}';
	}
}