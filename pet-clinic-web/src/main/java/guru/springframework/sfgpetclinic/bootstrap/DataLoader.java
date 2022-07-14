package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    @Override
    @Transactional
    public void run(String... args) {
        final int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = petTypeService.save(dog);

        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCat = petTypeService.save(cat);

        log.info("Loaded PetType...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        log.info("Loaded Speciality...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michel");
        owner1.setLastName("Winston");
        owner1.setAddress("123 London");
        owner1.setCity("1234");
        owner1.setTelephone("08974512");

        Pet mikePet = new Pet();
        mikePet.setName("Mike");
        mikePet.setOwner(owner1);
        mikePet.setBirthDate(LocalDate.now());
        mikePet.setPetType(savedDog);

        owner1.setPets(new HashSet<>(List.of(mikePet)));
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Stephen");
        owner2.setLastName("Elv");
        owner2.setAddress("123 Helsinki");
        owner2.setCity("5647");
        owner2.setTelephone("09547512");

        Pet fionaPet = new Pet();
        fionaPet.setName("Fiona");
        fionaPet.setOwner(owner2);
        fionaPet.setBirthDate(LocalDate.now());
        fionaPet.setPetType(savedCat);

        owner2.setPets(new HashSet<>(List.of(fionaPet)));
        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setDescription("Sneezy Cat");
        catVisit.setDate(LocalDate.now());
        catVisit.setPet(fionaPet);

        visitService.save(catVisit);

        log.info("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Frank");
        vet1.setLastName("Prop");
        vet1.setSpecialities(new HashSet<>(List.of(savedDentistry, savedRadiology)));
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Foo");
        vet2.setLastName("Bar");
        vet2.setSpecialities(new HashSet<>(List.of(savedDentistry, savedSurgery)));
        vetService.save(vet2);

        log.info("Loaded Vets...");
    }
}
