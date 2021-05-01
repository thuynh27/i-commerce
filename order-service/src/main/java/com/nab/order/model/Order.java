package com.nab.order.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "order_product")
@Builder
public class Order extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "user_email")	
	@JsonProperty("user_email")
	private String userEmail;

	@NotNull
	@Column(name = "cart_id")	
	@JsonProperty("cart_id")
	private String cartId;
	
    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<ProductDetail> productItems;
	
	@Column(name = "total_price")
	@JsonProperty("total_price")
	private BigDecimal totalPrice;
}
