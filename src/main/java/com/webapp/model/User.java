package com.webapp.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "users")
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private String username;

	@Column(name="nama_admin")
	private String namaAdmin;

	private String password;
	private boolean enabled;

	@Transient
	private String newPassword;
}
