package com.realshop.shop.dto;

import com.realshop.shop.constant.ItemSellStatus;
import com.realshop.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {
    private Long id;

    @NotBlank(message = "상품명을 반드시 입력해주세요.")
    private String itemNm;

    @NotNull(message = "가격을 반드시 입력해주세요")
    private Integer price;

    @NotBlank(message = "이름을 입력해주세요.")
    private String itemDetail;

    @NotNull(message = "재고를 입력해주세요.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }

}
