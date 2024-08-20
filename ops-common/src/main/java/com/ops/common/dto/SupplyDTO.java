package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SupplyDTO {

    private Long id;

    private Date createdDate;

    private Date deliveredDate;

    private Long createdBy;

    private Long receivedBy;

    private Long supplierId;

    private List<SupplyItemDTO> supplyItems;
}