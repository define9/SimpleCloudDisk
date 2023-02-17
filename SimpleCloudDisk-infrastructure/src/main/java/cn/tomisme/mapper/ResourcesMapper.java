package cn.tomisme.mapper;

import cn.tomisme.dataobject.Resources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResourcesMapper extends BaseMapper<Resources> {
    Integer getIdByHashAndSize(@Param("hash") String hash, @Param("size") Long size);
}
