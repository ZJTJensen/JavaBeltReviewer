package com.project.login.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class User{
	public User(){
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	@Id
	@GeneratedValue
	private long id;
	@Size(min=1,max=255,message="First name must be between 1-255 characters.")
	private String firstName;
	@Size(min=1,max=255,message="Last name must be between 1-255 characters.")
	private String lastName;
	@Size(min=1, message ="Location can not be blank")
	private String location;
	@Size(min=2, max=2, message ="Please use a valid state designatnion")
	private String state;
	@Email(message="Invalid Email. Ex: example@example.com")
	private String email;
	@Size(min=1,max=255,message="Password must be between 1-255 characters.")
	private String password;	

	private boolean admin;

	@Transient
	@Size(min=1,max=255)
	private String confirm;

	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;


 
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "attendees_events",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name="event_id")
	)
	private List<Event> events;

	@OneToMany(mappedBy="host", fetch = FetchType.LAZY)
	private List<Event> hostedEvents;
	
	@OneToMany(mappedBy="poster", fetch = FetchType.LAZY)
	private List<Message> comments;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
	public List<Event> geEvents(){
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public List<Event> getHostedEvents(){
		return hostedEvents;
	}
	public void setHostedEvents(List<Event> hostedEvents) {
		this.hostedEvents = hostedEvents;
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
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	public String getFirstName(){
		return this.firstName;
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
	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public void setConfirm(String confirm){
		this.confirm=confirm;
	}
	public String getConfirm(){
		return this.confirm;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setAdmin(boolean admin){
		this.admin=admin;
	}
	public boolean isAdmin(){
		return this.admin;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return this.password;
	}
}
