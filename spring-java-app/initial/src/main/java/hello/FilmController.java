package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;


@RestController
public class FilmController {
  private List<Film> Filme = new ArrayList<Film>();
	//int i=4;
	//String nume="";
  FilmController() {
    Film p1 = new Film(1, "Star Wars");
    Film p2 = new Film(2, "Groundhound Day");
    Film p3 = new Film(3, "Titanic");

    Filme.add(p1);
    Filme.add(p2);
    Filme.add(p3);
  }

  @RequestMapping(value="/Film", method = RequestMethod.GET)
  public List<Film> index() {
    return this.Filme;
  }
  
  @RequestMapping(value="/Film/{nume}", method = RequestMethod.POST)
  public ResponseEntity create(@PathVariable("nume") String nume) {
		Film n=new Film( Filme.size() +1,nume);
		Filme.add(n);
		
        return new ResponseEntity<Film>(n, new HttpHeaders(), HttpStatus.OK); 
  }

  @RequestMapping(value="/Film/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Film p : this.Filme) {
      if(p.getId() == id) {
        return new ResponseEntity<Film>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

    

@RequestMapping(value="/Film/{id}", method = RequestMethod.PUT)
  public ResponseEntity update( @PathVariable("id") int id) {
	  
	  for(Film p : this.Filme) {
		if(p.getId() == id) {
			p.setName("Twin Peaks");
        return new ResponseEntity<Film>(p, new HttpHeaders(), HttpStatus.OK); 
		}
	  }
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);	
  }


  @RequestMapping(value="/Film/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Film p : this.Filme) {
      if(p.getId() == id) {
        this.Filme.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}