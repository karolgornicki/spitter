package x.y.spitter.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Spittle {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Size(min = 1, max = 140, message = "The message cannot be longer than 140 characters.")
	private String text;
	@ManyToOne(fetch = FetchType.EAGER)
	private Spitter author;
	private Date createdAt;
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
		this.createdAt = Calendar.getInstance().getTime();
	}
	public Spitter getAuthor() {
		return author;
	}
	public void setAuthor(Spitter author) {
		this.author = author;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
