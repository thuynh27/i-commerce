package com.nab.product.model;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base abstract class for entities which will hold definitions for created,
 * last modified, created by, last modified by attributes.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@CreatedDate
	@JsonIgnore
	private Instant createdTime = Instant.now();

	@CreatedBy
	@JsonIgnore
	private String createdBy;

	@LastModifiedDate
	@JsonIgnore
	private Instant updatedTime = Instant.now();

	@LastModifiedBy
	@JsonIgnore
	private String updatedBy;

}
