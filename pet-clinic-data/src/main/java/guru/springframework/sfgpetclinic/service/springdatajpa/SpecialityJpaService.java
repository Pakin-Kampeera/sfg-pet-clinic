package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repository.SpecialityRepository;
import guru.springframework.sfgpetclinic.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
@RequiredArgsConstructor
public class SpecialityJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityRepository.findById(aLong).orElseThrow(() -> new RuntimeException("Speciality not found"));
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }
}
