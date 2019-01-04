package io.sandeep.blog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@NoArgsConstructor
@Builder
@Entity
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "tag")
public class Tag {

    @Id
    @SequenceGenerator(name = "tag_tag_id_seq", sequenceName = "tag_tag_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_tag_id_seq")
    @Column(name = "tag_id")
    @NotNull
    private int id;

    @Column(name = "tagname")
    @NotNull
    private String tagName;
//
//    @ManyToMany(mappedBy = "tags")
//    private List<Article> articles;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "modify_date")
    private Date modifyDate;

}
