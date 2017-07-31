package c4uCarSharing.gurami.services;

import c4uCarSharing.gurami.domain.Userd;

import c4uCarSharing.gurami.repositories.UserRepository;
import c4uCarSharing.gurami.services.UserService;
import c4uCarSharing.gurami.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }


    @Override
    public List<?> listAll() {
        List<Userd> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Userd getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public Userd saveOrUpdate(Userd domainObject) {
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }
        return userRepository.save(domainObject);
    }
    @Override
      @Transactional
       public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public Userd findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
