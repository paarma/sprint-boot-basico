package com.fundamentos.sprintboot.fundamentos.entity;

import javax.persistence.*;

/**
 * @Entity Representa el nombre de la entidad con la cual se referenciará la tabla en las consultas
 * (si no se agrega el atributo "name" entonces será el nombre de la clase por defecto)
 *
 * @Table Crea una tabla en BD con el nombre indicado en el "atributo name"
 * Ejemplo:
 *
 *  @Entity(name="MyEntityName")
 *  @Table(name="MyEntityTableName")
 *  class MyEntity {}
 *
 *  El SQL sería: select * from MyEntityName
 */
@Entity
@Table(name = "post")
public class Post {

    /**
     * Columna que representa el id de la tabla. Representa la llave primaria de la tabla.
     *
     *  @GeneratedValue = IDENTITY: permite que la base de datos genere un nuevo valor
     *  con cada operación de inserción.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false, unique = true)
    private Long id;

    @Column(name = "description", length = 255)
    private String description;

    /**
     * @ManyToOne: Representa la relación con otra entidad en una relación de
     * muchos a uno.
     */
    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
