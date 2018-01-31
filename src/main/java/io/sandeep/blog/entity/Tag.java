package io.sandeep.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@Entity
@AllArgsConstructor
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    @NotNull
    private int id;

    @Column(name = "tag")
    @NotNull
    private String tag;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "modify_date")
    private Date modifyDate;

}
