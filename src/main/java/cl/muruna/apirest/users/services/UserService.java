package cl.muruna.apirest.users.services;

import cl.muruna.apirest.phones.dto.CreatePhoneDTO;
import cl.muruna.apirest.phones.entities.PhoneEntity;
import cl.muruna.apirest.phones.services.PhoneService;
import cl.muruna.apirest.users.dto.CreateUserDTO;
import cl.muruna.apirest.users.dto.UserCreatedDTO;
import cl.muruna.apirest.users.entities.UserEntity;
import cl.muruna.apirest.users.exceptions.EmailExistsException;
import cl.muruna.apirest.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PhoneService phoneService;

    public List<UserEntity> getAllUsers () {
        return this.userRepository.findAll();
    }

    public UserCreatedDTO createUser (CreateUserDTO createUserDTO) {
        this.alreadyExistsEmail(createUserDTO.getEmail());
        UserEntity userEntity = this.userRepository.save(createUserDTO.toUserEntity());
        if(createUserDTO.getPhones() != null){
            for(CreatePhoneDTO createPhoneDTO : createUserDTO.getPhones()) {
                PhoneEntity phoneEntity = createPhoneDTO.toPhoneEntity();
                phoneEntity.setUserEntity(userEntity);
                this.phoneService.createPhone(phoneEntity);
            }
        }
        return userEntity.toCreatedResponse();
    }

    private void alreadyExistsEmail (String email) {
        Boolean exists = this.userRepository.findByEmail(email).isPresent();
        if(exists){
            throw new EmailExistsException("El email ya está registrado");
        }
    }
}
