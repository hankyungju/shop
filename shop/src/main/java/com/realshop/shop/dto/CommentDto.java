package com.realshop.shop.dto;

import com.realshop.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;

    private String body;

    private String name;

    private String email;

    private Long itemId;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
