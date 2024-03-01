package cl.muruna.apirest.phones.services;

import cl.muruna.apirest.phones.entities.PhoneEntity;
import cl.muruna.apirest.phones.repositories.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public void createPhone (PhoneEntity phoneEntity) {
        this.phoneRepository.save(phoneEntity);
    }
}
