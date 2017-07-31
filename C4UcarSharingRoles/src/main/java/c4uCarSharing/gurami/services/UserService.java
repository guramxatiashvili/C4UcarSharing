package c4uCarSharing.gurami.services;

import c4uCarSharing.gurami.domain.Userd;

public interface UserService extends CRUDService<Userd> {

    Userd findByUsername(String username);

}