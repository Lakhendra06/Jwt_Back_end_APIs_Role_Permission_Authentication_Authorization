package tms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tms.model.Role;
import tms.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        this.roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> optional = roleRepository.findById(id);
        Role role = null;
        if(optional.isPresent()){
            role = optional.get();
        }else{
            throw new RuntimeException("Role not found for id : " + id + ".");
        }
        return role;
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
    
}
