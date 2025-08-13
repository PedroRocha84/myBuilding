package pt.pedrorocha.mybuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.pedrorocha.mybuilding.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
