package io.daalchini.prometheuspoc.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "published")
    Boolean published;


}
