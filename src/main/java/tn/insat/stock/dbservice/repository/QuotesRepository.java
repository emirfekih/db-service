package tn.insat.stock.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import tn.insat.stock.dbservice.model.Quote;

import java.util.List;

@Repository
public interface QuotesRepository extends JpaRepository<Quote,Integer> {

      List<Quote> findByUserName (String username);

}
