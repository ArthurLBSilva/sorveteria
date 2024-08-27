    package sorveteria.service.generic;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;

    

    public abstract class GenericCrudService<T, ID, R extends JpaRepository<T, ID>> {

        protected final R repository;

        public GenericCrudService(R repository) {
            this.repository = repository;
        }

        public Page<T> listAll(Pageable pageable) {
            return repository.findAll(pageable);
        }

        public T listById(ID id) {
            return repository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Entity not found"));
        }

        public T create(T entity) {
            return repository.save(entity);
        }

        public T update(T entity, ID id) {
            if (!repository.existsById(id)) {
                throw new RuntimeException("Entity not found");
            }
            return repository.save(entity);
        }

        public void deleteById(ID id) {
            repository.deleteById(id);
        }
    }
