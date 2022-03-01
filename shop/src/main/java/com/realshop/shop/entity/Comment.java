package com.realshop.shop.entity;

import com.realshop.shop.dto.CommentDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="comment")
@Getter
@Setter
@ToString
public class Comment extends BaseEntity{
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="comment_body")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name="comment_time")
    private String commentTime;

    public static Comment writeComment(CommentDto commentDto, Item item){
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setItem(item);
        comment.setEmail(commentDto.getEmail());
        comment.setRegTime(LocalDateTime.now());
        comment.setCommentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return comment;
    }

    public Comment(Long id, String name, String body, Item item){
        this.id = id;
        this.name = name;
        this.body = body;
        this.setItem(item);
        this.setRegTime(LocalDateTime.now());
        this.setCommentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    public Comment(){

    }

    public void updateComment(CommentDto commentDto){
        this.name = commentDto.getName();
        this.body = commentDto.getBody();
    }
}
