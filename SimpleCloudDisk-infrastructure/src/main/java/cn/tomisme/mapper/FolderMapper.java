package cn.tomisme.mapper;

import cn.tomisme.dataobject.Folder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FolderMapper extends BaseMapper<Folder> {
    List<Folder> selectByParentId(@Param("userId") Integer userId, @Param("folderId") Integer folderId);

    Integer selectRootByUserId(int userId);
}
