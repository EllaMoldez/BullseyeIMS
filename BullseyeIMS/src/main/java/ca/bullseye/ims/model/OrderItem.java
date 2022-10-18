/*
 * package ca.bullseye.ims.model;
 * 
 * import java.math.BigDecimal;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.ManyToOne; import javax.persistence.Table; import
 * javax.validation.constraints.Digits; import
 * javax.validation.constraints.NotNull; import
 * javax.validation.constraints.Positive; import
 * javax.validation.constraints.PositiveOrZero;
 * 
 * @Entity
 * 
 * @Table(name = "order_items") public class OrderItem {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long oItemId;
 * 
 * @NotNull(message = "Order must not be null")
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "order_oId") private Order order;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "product_prodId") private Product product;
 * 
 * @NotNull(message = "Product amount must not be null")
 * 
 * @PositiveOrZero(message = "Invalid product amount")
 * 
 * @Column private Long amount;
 * 
 * @NotNull(message = "Product price must not be null")
 * 
 * @Digits(integer = 19, fraction = 2)
 * 
 * @Positive
 * 
 * @Column private BigDecimal price;
 * 
 * @NotNull(message = "Total product price must not be null")
 * 
 * @Digits(integer = 19, fraction = 2)
 * 
 * @Positive
 * 
 * @Column private BigDecimal totalPrice;
 * 
 * public Long getoItemId() { return oItemId; }
 * 
 * public void setoItemId(Long oItemId) { this.oItemId = oItemId; }
 * 
 * public Order getOrder() { return order; }
 * 
 * public void setOrder(Order order) { this.order = order; }
 * 
 * public Product getProduct() { return product; }
 * 
 * public void setProduct(Product product) { this.product = product; }
 * 
 * public Long getAmount() { return amount; }
 * 
 * public void setAmount(Long amount) { this.amount = amount; }
 * 
 * public BigDecimal getPrice() { return price; }
 * 
 * public void setPrice(BigDecimal price) { this.price = price; }
 * 
 * public BigDecimal getTotalPrice() { return totalPrice; }
 * 
 * public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice =
 * totalPrice; }
 * 
 * 
 * }
 */