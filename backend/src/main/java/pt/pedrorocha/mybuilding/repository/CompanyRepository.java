package pt.pedrorocha.mybuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.pedrorocha.mybuilding.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByAlias(String alias);

    boolean existsByvatNumber(Integer vatNumber);
}
