package cn.tomisme.domain.domainservice;

import cn.tomisme.dataobject.Role;
import cn.tomisme.enums.RoleType;
import cn.tomisme.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IService<Role> {

    /**
     * 带默认值
     * @param roleId
     * @return
     */
    @Cacheable("role")
    public Role getRoleWithDefault(Integer roleId) {
        Role role = baseMapper.selectById(roleId);
        if (role == null) {
            return baseMapper.selectById(RoleType.NORMAL);
        }
        return role;
    }
}
