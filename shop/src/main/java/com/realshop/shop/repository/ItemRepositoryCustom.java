package com.realshop.shop.repository;

import com.realshop.shop.dto.ItemSearchDto;
import com.realshop.shop.dto.MainItemDto;
import com.realshop.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
