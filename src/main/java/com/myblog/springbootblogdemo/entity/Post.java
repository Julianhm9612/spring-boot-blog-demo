package com.myblog.springbootblogdemo.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "post")
// @Where(clause = "user_id = 1")
@NamedQueries({ @NamedQuery(name = "Post.getByUserId", query = "select p from Post p where user.id = :id") })
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_post")
    @SequenceGenerator(name = "seq_post", sequenceName = "seq_post", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private BigInteger id;

    @Column(name = "title", nullable = false, length = 200)
    @NotNull(message = "El campo title es requerido")
    private String title;

    @Column(name = "body", nullable = false, length = 1000)
    @NotNull(message = "El campo body es requerido")
    private String body;

    // @Column(name = "user_id", nullable = false)
    // private BigInteger userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "El campo user es requerido")
    private UserEntity user;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // public BigInteger getUserId() {
    // return userId;
    // }

    // public void setUserId(BigInteger userId) {
    // this.userId = userId;
    // }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
