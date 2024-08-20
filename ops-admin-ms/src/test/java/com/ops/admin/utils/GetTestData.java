package com.ops.admin.utils;

import com.ops.admin.entities.*;
import com.ops.common.dto.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetTestData {

    public static Supply getSupply() {
        Supply supply = new Supply();
        supply.setId(1L);
        supply.setCreatedDate(new Date(1212121212121L));
        supply.setDeliveredDate(null);
        supply.setCreatedBy(1L);
        supply.setReceivedBy(null);
        supply.setSupplier(getSupplier());
        List<SupplyItem> supplyItems = new ArrayList<>();
        SupplyItem supplyItem = new SupplyItem();
        supplyItem.setId(1L);
        supplyItem.setQuantity(2);
        supplyItem.setDeliveryPrice(BigDecimal.valueOf(100));
        supplyItem.setProduct(getProduct());
        supplyItems.add(supplyItem);
        supply.setSupplyItems(supplyItems);
        return supply;
    }

    public static SupplyDTO getSupplyDTO() {
        SupplyDTO supplyDTO = new SupplyDTO();
        supplyDTO.setId(1L);
        supplyDTO.setCreatedDate(new Date(1212121212121L));
        supplyDTO.setDeliveredDate(null);
        supplyDTO.setCreatedBy(1L);
        supplyDTO.setReceivedBy(null);
        supplyDTO.setSupplierId(1L);
        List<SupplyItemDTO> supplyItems = new ArrayList<>();
        supplyItems.add(getSupplyItemDTO());
        supplyDTO.setSupplyItems(supplyItems);
        return supplyDTO;
    }


    public static SupplyItem getSupplyItem() {
        SupplyItem supplyItem = new SupplyItem();
        supplyItem.setId(1L);
        supplyItem.setQuantity(2);
        supplyItem.setDeliveryPrice(BigDecimal.valueOf(100));
        supplyItem.setSupply(getSupply());
        supplyItem.setProduct(getProduct());
        return supplyItem;
    }

    public static SupplyItemDTO getSupplyItemDTO() {
        SupplyItemDTO supplyItemDTO = new SupplyItemDTO();
        supplyItemDTO.setId(1L);
        supplyItemDTO.setQuantity(2);
        supplyItemDTO.setDeliveryPrice(BigDecimal.valueOf(100));
        supplyItemDTO.setProductId(1L);
        return supplyItemDTO;
    }

    public static Supplier getSupplier() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Test name");
        supplier.setPhoneNumber("0667213459");
        return supplier;
    }

    public static SupplierDTO getSupplierDTO() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(1L);
        supplierDTO.setName("Test name");
        supplierDTO.setPhoneNumber("0667213459");
        return supplierDTO;
    }

    public static Brand getBrand() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Test brand");
        return brand;
    }

    public static BrandDTO getBrandDTO() {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(1L);
        brandDTO.setName("Test brand");
        return brandDTO;
    }

    public static Category getCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Test category");
        List<Characteristic> characteristicList = new ArrayList<>();
        Characteristic characteristic = new Characteristic();
        characteristic.setId(1L);
        characteristic.setName("Test characteristic");
        Category parentCategory = new Category();
        parentCategory.setId(2L);
        parentCategory.setName("Test parent category");
        parentCategory.setCharacteristicList(new ArrayList<>());
        category.setCategory(parentCategory);
        characteristicList.add(characteristic);
        category.setCharacteristicList(characteristicList);
        return category;
    }

    public static CategoryDTO getCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test category");
        List<CharacteristicDTO> characteristicList = new ArrayList<>();
        CharacteristicDTO characteristicDTO = new CharacteristicDTO();
        characteristicDTO.setId(1L);
        characteristicDTO.setName("Test characteristic");
        CategoryDTO parentCategory = new CategoryDTO();
        parentCategory.setId(2L);
        parentCategory.setName("Test parent category");
        parentCategory.setCharacteristicList(new ArrayList<>());
        categoryDTO.setCategory(parentCategory);
        characteristicList.add(characteristicDTO);
        categoryDTO.setCharacteristicList(characteristicList);
        return categoryDTO;
    }

    public static Characteristic getCharacteristic() {
        Characteristic characteristic = new Characteristic();
        characteristic.setId(1L);
        characteristic.setName("Test characteristic");
        return characteristic;
    }

    public static CharacteristicDTO getCharacteristicDTO() {
        CharacteristicDTO characteristicDTO = new CharacteristicDTO();
        characteristicDTO.setId(1L);
        characteristicDTO.setName("Test characteristic");
        return characteristicDTO;
    }

    public static CharacteristicValue getCharacteristicValue() {
        CharacteristicValue characteristicValue = new CharacteristicValue();
        characteristicValue.setId(1L);
        characteristicValue.setValue("Test characteristic value");
        Characteristic characteristic = new Characteristic();
        characteristic.setId(1L);
        characteristic.setName("Test characteristic name");
        characteristicValue.setCharacteristic(characteristic);
        return characteristicValue;
    }

    public static CharacteristicValueDTO getCharacteristicValueDTO() {
        CharacteristicValueDTO characteristicValueDTO = new CharacteristicValueDTO();
        characteristicValueDTO.setId(1L);
        characteristicValueDTO.setValue("Test characteristic value");
        characteristicValueDTO.setCharacteristicId(1L);
        return characteristicValueDTO;
    }

    public static News getNews() {
        News news = new News();
        news.setId(1L);
        news.setTitle("Some text");
        // TODO when issue with images will fixed
//        news.setImg("Some img");
        news.setStartDate(new Date());
        news.setEndDate(new Date());

        return news;
    }

    public static NewsDTO getNewsDto() {
        NewsDTO newsDto = new NewsDTO();
        newsDto.setId(1L);
        newsDto.setTitle("Some text");
        // TODO when issue with images will fixed
//        newsDto.setImg("Some img");
        newsDto.setStartDate(new Date());
        newsDto.setEndDate(new Date());

        return newsDto;
    }

    public static Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test name");
        product.setQuantity(10);
        product.setBrand(getBrand());
        product.setCategory(getCategory());
        List<CharacteristicValue> characteristicValueList = new ArrayList<>();
        characteristicValueList.add(getCharacteristicValue());
        product.setCharacteristicValueList(characteristicValueList);
        return product;
    }

    public static ProductDTO getProductDTO() {
        ProductDTO product = new ProductDTO();
        product.setId(1L);
        product.setName("Test name");
        product.setQuantity(10);
        product.setBrandId(getBrand().getId());
        product.setCategoryId(getCategory().getId());
        List<CharacteristicValueDTO> characteristicValueList = new ArrayList<>();
        characteristicValueList.add(getCharacteristicValueDTO());
        product.setCharacteristicValuesList(characteristicValueList);
        return product;
    }
}
