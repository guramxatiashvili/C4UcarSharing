 package c4uCarSharing.gurami.services.mapservices;


import c4uCarSharing.gurami.domain.DomainObject;
import c4uCarSharing.gurami.domain.Userd;
import c4uCarSharing.gurami.services.UserService;
import c4uCarSharing.gurami.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@Profile("map")
public class UserServiceMapImpl extends AbstractMapService implements UserService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Userd getById(Integer id) {
        return (Userd) super.getById(id);
    }

    @Override
    public Userd saveOrUpdate(Userd domainObject) {

        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }

        return (Userd) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Userd findByUsername(String userName) {

        Optional returnUser =  domainMap.values().stream().filter(new Predicate<DomainObject>() {
            @Override
            public boolean test(DomainObject domainObject) {
                Userd user = (Userd) domainObject;
                return user.getUsername().equalsIgnoreCase(userName);
            }
        }).findFirst();

        return (Userd) returnUser.get();
    }
}