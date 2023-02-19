package cn.tomisme.mapper;

import cn.tomisme.dataobject.Folder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FolderMapper extends BaseMapper<Folder> {
    List<Folder> selectByParentId(Integer folderId);
}
