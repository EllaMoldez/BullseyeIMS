/*
 * package ca.bullseye.ims.model;
 * 
 * import java.util.Date; import java.util.List; import java.util.UUID;
 * 
 * import javax.persistence.*; import javax.validation.Valid; import
 * javax.validation.constraints.*;
 * 
 * 
 * @Entity
 * 
 * @Table(name = "order") public class Order {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long orderId;
 * 
 * @Column(nullable = false) private String oId;
 * 
 * @Temporal(TemporalType.TIMESTAMP)
 * 
 * @Column(nullable = false) private Date orderCreated;
 * 
 * @NotNull(message = "Employee must not be null")
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "employee_empId") private Employee employee;
 * 
 * @OneToMany
 * 
 * @JoinColumn(name = "product_prodId") private Product product;
 * 
 * @OneToOne
 * 
 * @JoinColumn(name = "supplier_supId") private Supplier supplier;
 * 
 * 
 * public Product getProduct() { return product; }
 * 
 * public void setProduct(Product product) { this.product = product; }
 * 
 * public Supplier getSupplier() { return supplier; }
 * 
 * public void setSupplier(Supplier supplier) { this.supplier = supplier; }
 * 
 * @Size(min = 1, message = "Order must have at least one product")
 * 
 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
 * "order", orphanRemoval = true) private List<@Valid OrderItem> orderItems;
 * 
 * @PrePersist public void prePersist() { this.oId =
 * UUID.randomUUID().toString(); this.orderCreated = new Date(); }
 * 
 * public Long getOrderId() { return orderId; }
 * 
 * public void setOrderId(Long orderId) { this.orderId = orderId; }
 * 
 * public String getoId() { return oId; }
 * 
 * public void setoId(String oId) { this.oId = oId; }
 * 
 * public Date getOrderCreated() { return orderCreated; }
 * 
 * public void setOrderCreated(Date orderCreated) { this.orderCreated =
 * orderCreated; }
 * 
 * public Employee getEmployee() { return employee; }
 * 
 * public void setEmployee(Employee employee) { this.employee = employee; }
 * 
 * public List<OrderItem> getOrderItems() { return orderItems; }
 * 
 * public void setOrderItems(List<OrderItem> orderItems) { this.orderItems =
 * orderItems; } }
 */