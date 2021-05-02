package com.nab.order.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "order_product_details")
@Builder
public class ProductDetail extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private Long productId;
	
	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;

	@NotNull
	@Column(name = "price", nullable = false, precision = 9, scale = 2)
	private BigDecimal price;

	@NotNull
	private int availability;

	private String brand;

	private String color;

	private String description;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
	private Order order;

	public ProductDetail(Long id, @NotNull Long productId, String productName, @NotNull BigDecimal price,
			@NotNull int availability, String brand, String color, String description) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.availability = availability;
		this.brand = brand;
		this.color = color;
		this.description = description;
	}  
}
