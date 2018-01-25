package com.project.login.models;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@Size(min=1,max=255,message="Username must be between 1-255 characters.")
	private String username;
	@Email(message="Invalid Email. Ex: example@example.com")
	private String email;
	@Size(min=1,max=255,message="Password must be between 1-255 characters.")
	private String password;
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date lastPickedUp;	
	
	private int admin;

	
	@OneToMany(mappedBy="maker", fetch = FetchType.LAZY)
	private List<Ring> rings;
	
	@OneToMany(mappedBy="bearer", fetch = FetchType.LAZY)
	private List<Ring> theRing;
	
	
	@OneToMany(mappedBy="fellowship", fetch = FetchType.LAZY)
    private List<Team> teams;

	@Transient
	@Size(min=1,max=255)
	private String confirm;

	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Date getLastPickedUp() {
		return lastPickedUp;
	}
	public void setLastPickedUp(Date lastPickedUp) {
		this.lastPickedUp = lastPickedUp;
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
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return this.username;
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
	public void setAdmin(int admin){
		this.admin=admin;
	}
	public int getAdmin(){
		return this.admin;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return this.password;
	}
	/**
	 * @return the rings
	 */
	public List<Ring> getRings() {
		return rings;
	}
	/**
	 * @param rings the rings to set
	 */
	public void setRings(List<Ring> rings) {
		this.rings = rings;
	}
	/**
	 * @return the theRing
	 */
	public  List<Ring> getTheRing() {
		return theRing;
	}
	/**
	 * @param theRing the theRing to set
	 */
	public void setTheRing( List<Ring> theRing) {
		this.theRing = theRing;
	}
	/**
	 * @return the teams
	 */
	public List<Team> getTeams() {
		return teams;
	}
	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
