package com.nab.tracking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document("product_tracking")
public class ProductTracking extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("product_id")
	@Field("product_id")
	private Long productID;
	
	@JsonProperty("user_email")
	@Field("user_email")
	private String userEmail;
	
	@DBRef
	@JsonProperty("product_view")
	@Field("product_view")
	private ProductView productView;
}
