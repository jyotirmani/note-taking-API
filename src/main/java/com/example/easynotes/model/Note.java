package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by JS on 25/05/21.
 */
@Entity
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration_Date;

    @NotBlank
    private String owner;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;



    /**
	 * 
	 */
	public Note() {
		super();
	}

	/**
	 * @param id
	 * @param title
	 * @param content
	 * @param createdAt
	 * @param updatedAt
     * @param expiration_Date
     * @param owner
	 */
	public Note(Long id, @NotBlank String title, @NotBlank String content, Date createdAt, Date updatedAt, Date expiration_Date, String owner) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
        this.expiration_Date = expiration_Date;
        this.owner = owner;
	}

	public Note(@NotBlank String title, @NotBlank String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getExpiration_Date() { return expiration_Date; }

    public void setExpiration_Date(Date expiration_Date) {this.expiration_Date = expiration_Date; }

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }

    public Category getCategory() { return category;}

    public void setCategory(Category category) { this.category = category;}
}
