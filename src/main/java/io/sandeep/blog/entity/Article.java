package io.sandeep.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@Entity
@AllArgsConstructor
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    @NotNull
    protected int id;

    @Column(name = "title")
    @NotNull
    protected String title;

    @Column(name = "content")
    @NotNull
    @Lob
    private String content;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    @JsonIgnore
    protected User author;

    @NotNull
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "article_tag", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "modify_date")
    private Date modifyDate;


}
