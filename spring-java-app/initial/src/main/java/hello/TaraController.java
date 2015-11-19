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
public class TaraController {
  private List<Tara> tari = new ArrayList<Tara>();
	//int i=4;
	//String nume="";
  TaraController() {
    Tara p1 = new Tara(1, "Norvegia");
    Tara p2 = new Tara(2, "Finlanda");
    Tara p3 = new Tara(3, "Spania");

    tari.add(p1);
    tari.add(p2);
    tari.add(p3);
  }

  @RequestMapping(value="/Tara", method = RequestMethod.GET)
  public List<Tara> index() {
    return this.tari;
  }
  
  @RequestMapping(value="/Tara/{nume}", method = RequestMethod.POST)
  public ResponseEntity create(@PathVariable("nume") String nume) {
		Tara n=new Tara( tari.size() +1,nume);
		tari.add(n);
		
        return new ResponseEntity<Tara>(n, new HttpHeaders(), HttpStatus.OK); 
  }

  @RequestMapping(value="/Tara/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Tara p : this.tari) {
      if(p.getId() == id) {
        return new ResponseEntity<Tara>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

    

@RequestMapping(value="/Tara/{id}", method = RequestMethod.PUT)
  public ResponseEntity update( @PathVariable("id") int id) {
	  
	  for(Tara p : this.tari) {
		if(p.getId() == id) {
			p.setName("Jamaica");
        return new ResponseEntity<Tara>(p, new HttpHeaders(), HttpStatus.OK); 
		}
	  }
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);	
  }


  @RequestMapping(value="/Tara/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Tara p : this.tari) {
      if(p.getId() == id) {
        this.tari.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}