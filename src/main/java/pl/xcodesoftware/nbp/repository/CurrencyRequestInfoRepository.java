package pl.xcodesoftware.nbp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.xcodesoftware.nbp.entity.CurrencyRequestInfoEntity;

@Repository
public interface CurrencyRequestInfoRepository extends JpaRepository<CurrencyRequestInfoEntity, Long> { }
