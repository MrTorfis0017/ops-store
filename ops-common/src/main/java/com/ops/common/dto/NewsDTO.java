package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewsDTO {

    private Long id;

    private String title;

    private String createdBy;

//    private String img;

    private Date startDate;

    private Date endDate;
}
