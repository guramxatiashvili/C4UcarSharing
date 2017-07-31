package c4uCarSharing.gurami.services.jpaservices;

import c4uCarSharing.gurami.domain.Userd;
import c4uCarSharing.gurami.services.UserService;
import c4uCarSharing.gurami.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Userd", Userd.class).getResultList();
    }

    @Override
    public Userd getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Userd.class, id);
    }

    @Override
    public Userd saveOrUpdate(Userd domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }

        Userd saveduser = em.merge(domainObject);
        em.getTransaction().commit();

        return saveduser;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Userd.class, id));
        em.getTransaction().commit();
    }

    @Override
    public Userd findByUsername(String userName) {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Userd where username = :username", Userd.class).setParameter("username", userName).getSingleResult();
    }
}