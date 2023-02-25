package cn.tomisme.domain.model.response.cloud;

import cn.tomisme.enums.StorageNodeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StdConfigNodeDto {
    private Integer nodeId;
    private Integer sort;
    private String nodeName;
    private Integer nodeTypeId;
    private String nodeTypeName;
    private String nodeLocalPath;
    /**
     * 剩余容量
     */
    private Long RemainingCapacity;
    private Boolean disable;
}
