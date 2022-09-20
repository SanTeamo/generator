package cn.santeamo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接收分页参数
 *
 * @author shenle
 */
@ApiModel(value = "分页参数")
@Data
public class PageParam {

    @ApiModelProperty(value = "页码")
    int page;

    @ApiModelProperty(value = "每页数目")
    int size;

    @ApiModelProperty(value = "排序")
    String orderBy;
}
