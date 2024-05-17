package com.gaoge.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_knowledge")
@ApiModel
public class Knowledge {
    @Id
    private Integer knowledge_id;
    private String title;
    private String content;
    private String pic_path;
    private String own_name;
    private Date create_time;
    private Date update_time;
}
