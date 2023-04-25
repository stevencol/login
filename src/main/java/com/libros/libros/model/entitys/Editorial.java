package com.libros.libros.model.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "editoriales")
@EntityListeners(AuditingEntityListener.class)
public class Editorial implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "editorial_id")
	private Long id;

	@Column(name = "nombre")
	@NotEmpty
	private String nombre;

	@OneToMany(mappedBy = "editorial", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	// @ToString.Exclude
	// @JsonIgnore

	private List<Libro> libros;

	@CreatedDate
	@Column(name = "created_date")
	private Date createdDate;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	@Temporal(TemporalType.DATE)
	private Date lastModifiedDate;

	@Override
	public String toString() {
		return "Editoroial[id=" + this.id + "Nombre" + this.nombre + "]";
	}

}
