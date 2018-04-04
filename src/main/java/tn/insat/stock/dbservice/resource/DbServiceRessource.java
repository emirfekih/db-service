package tn.insat.stock.dbservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.insat.stock.dbservice.model.Quote;
import tn.insat.stock.dbservice.model.Quotes;
import tn.insat.stock.dbservice.repository.QuotesRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceRessource {

    @Autowired
    private QuotesRepository quotesRepository;

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {
       return getQuotesByUserName(username);

    }

    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
                .stream().map(Quote::getQuote).collect(Collectors.toList());

    }


    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){

        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(),quote))
                .forEach(quote -> quotesRepository.save(quote));

        return( getQuotesByUserName(quotes.getUserName()));

    }

}
