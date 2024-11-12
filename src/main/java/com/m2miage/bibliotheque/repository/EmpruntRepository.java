
package com.m2miage.bibliotheque.repository;

        import com.m2miage.bibliotheque.entity.Emprunt;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByArchive(boolean archive);
}

