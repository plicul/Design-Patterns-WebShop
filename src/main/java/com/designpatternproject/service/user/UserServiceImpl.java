package com.designpatternproject.service.user;

import com.designpatternproject.dto.user.AddressDto;
import com.designpatternproject.dto.user.ContactDto;
import com.designpatternproject.dto.user.UserDto;
import com.designpatternproject.entity.*;
import com.designpatternproject.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final PostalCodeRepository postalCodeRepository;
    private final CountryRepository countryRepository;

    public UserServiceImpl(UserRepository userRepository,
                           ContactRepository contactRepository,
                           AddressRepository addressRepository,
                           CityRepository cityRepository,
                           PostalCodeRepository postalCodeRepository,
                           CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.postalCodeRepository = postalCodeRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public UserDto getUserDto(String userName) throws Exception {
        User user = userRepository.findByName(userName).orElseThrow(() -> new Exception("User not found"));

        Optional<Contact> contactOptional = contactRepository.findFirstByUser_IdOrderByIdDesc(user.getId());
        Optional<Address> addressOptional = addressRepository.findFirstByUser_IdOrderByIdDesc(user.getId());

        ContactDto contactDto = null;
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            contactDto = new ContactDtoBuilder()
                    .withId(contact.getId())
                    .withPhoneNumber(contact.getPhoneNumber())
                    .withEmail(contact.getEmail())
                    .withFirstName(contact.getFirstName())
                    .withLastName(contact.getLastName())
                    .withCreationDate(contact.getCreationDate())
                    .build();
        }

        AddressDto addressDto = null;
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            addressDto = new AddressDtoBuilder()
                    .withId(address.getId())
                    .withStreetAddress(address.getStreetAddress())
                    .withCity(address.getCity().getCity())
                    .withPostalCode(address.getPostalCode().getPostalCode())
                    .withCountry(address.getCountry().getCountryName())
                    .withCreationDate(address.getCreationDate())
                    .build();
        }

        return new UserDtoBuilder()
                .withId(user.getId())
                .withName(user.getName())
                //.withPassword(user.getPassword())
                //.withType(user.getType())
                .withContact(contactDto)
                .withAddress(addressDto)
                .build();
    }

    @Override
    @Transactional("jpaTransactionManager")
    public void updateUser(UserDto userDto) throws Exception {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new Exception("User not found"));

        user.setName(userDto.getName());

        user = userRepository.saveAndFlush(user);

        //custom logika za provjeru je li sifra mjenjana
        //ako je onda se izraduje hash i to sprema tu
        //ako nije mjenjana biti ce null polje
        //user.setPassword(userDto.getPassword());

        //za sada se ne moze mjenjati
        //user.setType(userDto.getType());

        if (userDto.getContact() != null) {
            ContactDto contactDto = userDto.getContact();
            Contact contact = new Contact();
            if(contactDto.getId() != null){
                contact = contactRepository.findById(contactDto.getId()).get();
            }

            contact.setUser(user);
            contact.setPhoneNumber(contactDto.getPhoneNumber());
            contact.setEmail(contactDto.getEmail());
            contact.setFirstName(contactDto.getFirstName());
            contact.setLastName(contactDto.getLastName());
            contact.setCreationDate(contactDto.getCreationDate() == null ? LocalDate.now() : contactDto.getCreationDate());

            contactRepository.save(contact);
        }

        if (userDto.getAddress() != null) {
            AddressDto addressDto = userDto.getAddress();
            Address address = new Address();
            if(addressDto.getId() != null){
                address = addressRepository.findById(addressDto.getId()).get();
            }

            address.setUser(user);

            address.setStreetAddress(addressDto.getStreetAddress());

            City city = cityRepository.findByCity(addressDto.getCity());
            address.setCity(city);

            PostalCode postalCode = postalCodeRepository.findByPostalCode(addressDto.getPostalCode());
            address.setPostalCode(postalCode);

            Country country = countryRepository.findByCountryName(addressDto.getCountry());
            address.setCountry(country);

            address.setCreationDate(addressDto.getCreationDate() == null ? LocalDate.now() : addressDto.getCreationDate());

            addressRepository.save(address);
        }

    }

}
