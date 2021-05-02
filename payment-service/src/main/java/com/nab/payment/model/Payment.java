package com.nab.payment.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "payment")
@Builder
public class Payment extends AbstractEntity{
	/**
	 * 
	 */
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
	@Column(name = "order_id")	
	@JsonProperty("order_id")
	private Long orderId;
	
	@Column(name = "total_price")
	@JsonProperty("total_price")
	private BigDecimal totalPrice;
	
	@NotNull
	@Column(name = "oder_status")
	@JsonProperty("oder_status")
	private String  orderStatus;

}
