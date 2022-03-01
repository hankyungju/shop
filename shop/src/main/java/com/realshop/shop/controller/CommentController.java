package com.realshop.shop.controller;

import com.realshop.shop.dto.CommentDto;
import com.realshop.shop.dto.OrderDto;
import com.realshop.shop.entity.Comment;
import com.realshop.shop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
/*
    @PostMapping(value = "/comment")
    public @ResponseBody ResponseEntity comment(@RequestBody @Valid CommentDto commentDto,
                         BindingResult bindingResult, Principal principal){

        if (bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long commentId;

        try {
            commentId = commentService.createComment(commentDto);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        List<Comment> commentList = commentService.getCommentList(commentDto.getItemId());
        return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);
    }

 */
    @RequestMapping(value = "/comment", method = { RequestMethod.POST })
    public String comment(Model model, @RequestParam Map<String, Object> paramMap, Principal principal){
        CommentDto commentDto = new CommentDto();
        commentDto.setItemId(Long.valueOf(paramMap.get("itemId").toString()));
        commentDto.setName(paramMap.get("name").toString());
        commentDto.setBody(paramMap.get("body").toString());
        try {
            commentDto.setEmail(principal.getName());
        } catch (NullPointerException e){
            commentDto.setEmail("Anonymous");
        }
        commentService.createComment(commentDto);

        model.addAttribute("commentList", commentService.getCommentList(commentDto.getItemId()));

        return "/item/itemDtl :: #refresher";
    }

    @RequestMapping(value = "/comment/delete", method = { RequestMethod.POST })
    public String commentDelete(Model model, @RequestParam Map<String, Object> paramMap, Principal principal){

        System.out.println("호출.");
        CommentDto commentDto = new CommentDto();
        commentDto.setId(Long.valueOf(paramMap.get("id").toString()));
        commentDto.setItemId(Long.valueOf(paramMap.get("itemId").toString()));
        try {
            commentDto.setEmail(principal.getName());
        } catch (NullPointerException e){
            commentDto.setEmail("Anonymous");
        }
        commentService.deleteComment(commentDto);

        model.addAttribute("commentList",  commentService.getCommentList(commentDto.getItemId()));

        return "/item/itemDtl :: #refresher";

    }
}

