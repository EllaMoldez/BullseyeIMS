package ca.bullseye.ims.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@CreatedDate
	private Date orderDate;
	
	private int orderQuantity;
	
	
	

}
