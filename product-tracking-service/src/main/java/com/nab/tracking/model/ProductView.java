package com.nab.tracking.model;

import org.springframework.data.annotation.Id;
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
@Document("product_view")
public class ProductView extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("activity")
	@Field("activity")
	private String activity;
	
	private String params;
	
	private String description;
}
