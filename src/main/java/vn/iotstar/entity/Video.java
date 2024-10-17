package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VideoId")
	private int videoId;
	
	@Column(name = "Title", columnDefinition = "NVARCHAR(50) NOT NULL")
	@NotEmpty(message = "Không được phép rỗng")
	private String title;

	@Column(name = "Description", columnDefinition = "NVARCHAR(500)")
	private String description;

	@Column(name = "Poster", columnDefinition = "NVARCHAR(100)")
	private String poster;
	
	@Column(name = "Active")
	private boolean active;
	
	@Column(name = "Views")
	private int views;

	// bi-directional many-to-one association to Category
//	@ManyToOne
//	@JoinColumn(name = "CategoryId")
//	private Category category;
}
