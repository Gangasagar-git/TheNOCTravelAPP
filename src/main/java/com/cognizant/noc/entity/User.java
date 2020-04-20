package com.cognizant.noc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="USER")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private long phoneNo;

    @NotBlank(message = "From Address is mandatory")
    @Column(name = "from_address")
    private String from;

    @NotBlank(message = "To Address is mandatory")
    @Column(name = "to_address")
    private String to;

    @NotBlank(message = "Purpose is mandatory")
    @Column(name = "purpose")
    private String purpose;

    @NotBlank(message = "Vehicle Number is mandatory")
    @Column(name = "vehicle_no")
    private String vehicleNo;

    @NotBlank(message = "Present City is mandatory")
    @Column(name = "present_city")
    private String presentCity;
    
  	@Column(name="status")
  	private String status;
  	
  	@Column(name="discription")
  	private  String discription;
  	
  	@Column(name="createddate")
  	private String createddate;
  	
  	@Column(name="statusDate")
  	private String statusDate;
    
    public User() {}

	public User(long id, @NotBlank(message = "Name is mandatory") String name, String email, long phoneNo,
			@NotBlank(message = "From Address is mandatory") String from,
			@NotBlank(message = "To Address is mandatory") String to,
			@NotBlank(message = "Purpose is mandatory") String purpose,
			@NotBlank(message = "Vehicle Number is mandatory") String vehicleNo,
			@NotBlank(message = "Present City is mandatory") String presentCity, String status, String discription,
			String createddate, String statusDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.from = from;
		this.to = to;
		this.purpose = purpose;
		this.vehicleNo = vehicleNo;
		this.presentCity = presentCity;
		this.status = status;
		this.discription = discription;
		this.createddate = createddate;
		this.statusDate = statusDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getPresentCity() {
		return presentCity;
	}

	public void setPresentCity(String presentCity) {
		this.presentCity = presentCity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", from=" + from
				+ ", to=" + to + ", purpose=" + purpose + ", vehicleNo=" + vehicleNo + ", presentCity=" + presentCity
				+ ", status=" + status + ", discription=" + discription + ", createddate=" + createddate
				+ ", statusDate=" + statusDate + "]";
	}

    
    
}