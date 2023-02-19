package cn.tomisme.mapper;

import cn.tomisme.dataobject.Folder;
import cn.tomisme.dataobject.UserResources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserResourcesMapper extends BaseMapper<UserResources> {
    List<UserResources> selectByFolderId(@Param("userId") int userId, @Param("folderId") Integer folderId);
}
