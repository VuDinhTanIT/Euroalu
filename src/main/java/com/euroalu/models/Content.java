package com.euroalu.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    private Long id; // ID tự động tăng

    private String title; // Tiêu đề phần mở đầu

    //Có thể tìm kiểm theo href thẻ a
    private String path;
    
    private String content; // Nội dung

    private String image; // Đường dẫn ảnh
}