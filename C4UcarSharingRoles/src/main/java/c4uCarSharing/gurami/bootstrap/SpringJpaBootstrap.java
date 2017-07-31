package c4uCarSharing.gurami.bootstrap;

import c4uCarSharing.gurami.domain.Trip;
import c4uCarSharing.gurami.domain.Role;
import c4uCarSharing.gurami.domain.Userd;
import c4uCarSharing.gurami.repositories.TripRepository;
import c4uCarSharing.gurami.services.RoleService;
import c4uCarSharing.gurami.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private TripRepository productRepository;
    private UserService userService;
    private RoleService roleService;

    private Logger log = Logger.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setProductRepository(TripRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadUsers();
        loadRoles();
        assignUsersToUserRole();
        assignUsersToAdminRole();
    }

        private void loadProducts() {
        Trip trip1 = new Trip();
        trip1.setFromLocation("chemnitz");
        trip1.setTripDate("01.08.2017");
        trip1.setPrice(new BigDecimal("18"));
        trip1.setToLocation("berlin");
        productRepository.save(trip1);

        log.info("Saved Trip - id: " + trip1.getId());

        Trip trip2 = new Trip();
        trip2.setFromLocation("chemnitz");
        trip2.setTripDate("07.06.2071");
        trip2.setPrice(new BigDecimal("11"));
        trip2.setToLocation("prague");
        productRepository.save(trip2);

        log.info("Saved another Trip - id:" + trip2.getId());
    } 
     

    private void loadUsers() {
        Userd user1 = new Userd();
        user1.setUsername("user");
        user1.setPassword("user");
        userService.saveOrUpdate(user1);

        Userd user2 = new Userd();
        user2.setUsername("admin");
        user2.setPassword("admin");
        userService.saveOrUpdate(user2);

    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);
        log.info("Saved role" + role.getRole());
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
        log.info("Saved role" + adminRole.getRole());
    }
    private void assignUsersToUserRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<Userd> users = (List<Userd>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("user")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<Userd> users = (List<Userd>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
}
