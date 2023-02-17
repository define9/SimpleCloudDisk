package cn.tomisme.mapper;

import cn.tomisme.dataobject.StorageNodeConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageNodeConfigMapper extends BaseMapper<StorageNodeConfig> {
    List<StorageNodeConfig> selectAll();
}
