package com.realshop.shop.service;

import com.realshop.shop.dto.CommentDto;
import com.realshop.shop.dto.OrderHistDto;
import com.realshop.shop.dto.OrderItemDto;
import com.realshop.shop.entity.*;
import com.realshop.shop.repository.CommentRepository;
import com.realshop.shop.repository.ItemRepository;
import com.realshop.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    public Long createComment(CommentDto commentDto) {
        Item item = itemRepository.findById(commentDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        Comment comment = Comment.writeComment(commentDto, item);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentList(Long itemId){
        List<Comment> commentList = commentRepository.findByItemIdOrderByIdAsc(itemId);
        return commentList;
    }
    /*
    public Page<CommentDto> getCommentList(Long itemId, Pageable pageable){
        List<Comment> commentList = commentRepository.findByItemIdOrderByIdAsc(itemId);
        Long totalCount = commentRepository.countComment(itemId);
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (Comment comment : commentList){
            CommentDto commentDto = new CommentDto();
            commentDto.setItemId(comment.getItem().getId());
            commentDto.setName(comment.getName());
            commentDto.setBody(comment.getBody());
            commentDto.setName(comment.getName());
            commentDto.setEmail(comment.getEmail());
            commentDtoList.add(commentDto);
        }

        return new PageImpl<CommentDto>(commentDtoList, pageable, totalCount);
    }
    */


    public Long editComment(CommentDto commentDto){
        Comment comment = commentRepository.findById(commentDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        comment.updateComment(commentDto);
        return comment.getId();
    }

    public void deleteComment(CommentDto commentDto){
        Comment comment = commentRepository.findById(commentDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        if(validateCommentUser(comment.getId(), commentDto.getEmail())) {
            commentRepository.delete(comment);
        }
    }

    @Transactional(readOnly = true)
    public boolean validateCommentUser(Long commentId, String email){

        Comment comment = commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        if (StringUtils.equals(email, "Anonymous") &&
                StringUtils.equals(comment.getEmail(), "Anonymous")){
            return true;
        } else if (StringUtils.equals(email, "Anonymous")){
            return false;
        }

        if(!StringUtils.equals(member.getEmail(), comment.getEmail())){
            return false;
        }

        return true;
    }



}
