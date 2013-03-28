package ie.cit.adf.domain;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bookmark")
public class Bookmark {
	private String id;
	private String text;
	private boolean done;

	public Bookmark() {
		id = UUID.randomUUID().toString();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
