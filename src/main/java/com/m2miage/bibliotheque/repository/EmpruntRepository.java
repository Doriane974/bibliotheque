
package com.m2miage.bibliotheque.repository;

        import com.m2miage.bibliotheque.entity.Emprunt;
        import com.m2miage.bibliotheque.entity.Reservation;
        import com.m2miage.bibliotheque.entity.Usager;
        import org.springframework.data.domain.Sort;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    //List<Emprunt> findByArchive(boolean archive);
    List<Emprunt> findByUsagerId(Long usagerId);
    List<Emprunt> findByExemplaireId(Long exemplaireId);
    List<Emprunt> findByArchive(boolean archive, Sort sort);
}

