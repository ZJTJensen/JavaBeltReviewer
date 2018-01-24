package com.project.login.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Event{
	@Id
	@GeneratedValue
	private long id;
	@Size(min=1, message="You need a name")
	private String name;
	private String sDate;
	private Date date;
	@Size(min=1, message ="Location can not be blank")
	private String location;
	@Size(min=2, max=2, message ="Please use a valid state designatnion")
	private String state;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User host;
	
	@OneToMany(mappedBy="subject", fetch = FetchType.LAZY)
	private List<Message> comments;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "attendees_events",
		joinColumns = @JoinColumn(name="event_id"),
		inverseJoinColumns = @JoinColumn(name="user_id")
	)
	private List<User> attendees;

	// Member variables and annotations go here.
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}

	public User getHost(){
		return host;
	}
	public void setHost(User host) {
		this.host = host;
	}
	public List<User> getAttendees(){
		return attendees;
	}
	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
	public List<Message> getComments(){
		return comments;
	}
	public void setComments(List<Message> comments) {
		this.comments = comments;
	}

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setLocation(String location){
		this.location=location;
	}
	public String getLocation(){
		return this.location;
	}
	public void setState(String state){
		this.state=state;
	}
	public String getState(){
		return this.state;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	public void setDate(Date date){
		this.date=date;
	}
	public Date getDate(){
		return this.date;
	}
	// Setters and Getters go here
	
	public Event(){
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	/**
	 * @return the sDate
	 */
	public String getsDate() {
		return sDate;
	}
	/**
	 * @param sDate the sDate to set
	 */
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
}
