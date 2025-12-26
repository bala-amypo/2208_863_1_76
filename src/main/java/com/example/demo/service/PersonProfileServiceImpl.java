@Service
public class PersonProfileServiceImpl implements PersonProfileService {

    private final PersonProfileRepository repository;

    public PersonProfileServiceImpl(PersonProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonProfile createPerson(PersonProfile person) {

        if (person.getEmail() == null || person.getEmail().isBlank()) {
            throw new ApiException("Email is required");
        }

        if (repository.existsByEmail(person.getEmail())) {
            throw new ApiException("Duplicate email");
        }

        if (person.getReferenceId() != null &&
                repository.findByReferenceId(person.getReferenceId()).isPresent()) {
            throw new ApiException("Duplicate referenceId");
        }

        if (person.getRelationshipDeclared() == null) {
            person.setRelationshipDeclared(false);
        }

        return repository.save(person);
    }

    @Override
    public PersonProfile getPersonById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Person not found"));
    }

    @Override
    public List<PersonProfile> getAllPersons() {
        return repository.findAll();
    }

    @Override
    public PersonProfile findByReferenceId(String referenceId) {
        return repository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ApiException("ReferenceId not found"));
    }

    @Override
    public PersonProfile updateRelationshipDeclared(Long id, boolean declared) {
        PersonProfile person = getPersonById(id);
        person.setRelationshipDeclared(declared);
        return repository.save(person);
    }
}
