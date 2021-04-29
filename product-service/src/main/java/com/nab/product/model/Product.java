package com.nab.product.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "product")
@Builder
public class Product extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "product_name")
	private String productName;

	@NotNull
	@Column(name = "price", nullable = false, precision = 9, scale = 2)
	private BigDecimal price;

	@NotNull
	private int availability;

	private String brand;

	private String color;

	private String description;

	@Column(name = "image_url")
	private String imageUrl;

}
