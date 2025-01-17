package br.com.jway.geraesooh.model; 

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="login")
	private String login;

	@Column(name="nivel")
	private String nivel;

	@Column(name="password")
	private String password;

	@Column(name="tenancy")
	private Long tenancy;
	
	public Long getId() { 
		return id;
	}
	public void  setId(Long id) { 
		this.id = id;
	}

	public String getLogin() { 
		return login;
	}
	public void  setLogin(String login) { 
		this.login = login;
	}

	public String getNivel() { 
		return nivel;
	}
	public void  setNivel(String nivel) { 
		this.nivel = nivel;
	}

	public String getPassword() { 
		return password;
	}
	public void  setPassword(String password) { 
		this.password = password;
	}

	public Long getTenancy() { 
		return tenancy;
	}
	public void  setTenancy(Long tenancy) { 
		this.tenancy = tenancy;
	}
}