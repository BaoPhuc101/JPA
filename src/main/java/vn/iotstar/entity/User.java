package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT c FROM User c")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	private int userId;

	@Column(name = "Username", columnDefinition = "NVARCHAR(50) NOT NULL")
	@NotEmpty(message = "Không được phép rỗng")
	private String username;

	@Column(name = "Password", columnDefinition = "NVARCHAR(50) NULL")
	private String password;
	
	@Column(name = "Images", columnDefinition = "NVARCHAR(500) NULL")
	private String images;
	
	@Column(name = "Fullname", columnDefinition = "NVARCHAR(100) NULL")
	private String fullname;
	
	@Column(name = "Email", columnDefinition = "NVARCHAR(50) NULL")
	private String email;
	
	@Column(name = "Phone", columnDefinition = "NVARCHAR(50) NULL")
	private String phone;
	
	@Column(name = "CreatedDate")
	private Date createdDate;
}
