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
public class OceanController {
  private List<Ocean> Oceane = new ArrayList<Ocean>();
	//int i=4;
	//String nume="";
  OceanController() {
    Ocean p1 = new Ocean(1, "Atlantic");
    Ocean p2 = new Ocean(2, "Pacific");
    Ocean p3 = new Ocean(3, "Arctic");

    Oceane.add(p1);
    Oceane.add(p2);
    Oceane.add(p3);
  }

  @RequestMapping(value="/Ocean", method = RequestMethod.GET)
  public List<Ocean> index() {
    return this.Oceane;
  }
  
  @RequestMapping(value="/Ocean/{nume}", method = RequestMethod.POST)
  public ResponseEntity create(@PathVariable("nume") String nume) {
		Ocean n=new Ocean( Oceane.size() +1,nume);
		Oceane.add(n);
		
        return new ResponseEntity<Ocean>(n, new HttpHeaders(), HttpStatus.OK); 
  }

  @RequestMapping(value="/Ocean/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Ocean p : this.Oceane) {
      if(p.getId() == id) {
        return new ResponseEntity<Ocean>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

    

@RequestMapping(value="/Ocean/{id}", method = RequestMethod.PUT)
  public ResponseEntity update( @PathVariable("id") int id) {
	  
	  for(Ocean p : this.Oceane) {
		if(p.getId() == id) {
			p.setName("Oceanul Indian");
        return new ResponseEntity<Ocean>(p, new HttpHeaders(), HttpStatus.OK); 
		}
	  }
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);	
  }


  @RequestMapping(value="/Ocean/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Ocean p : this.Oceane) {
      if(p.getId() == id) {
        this.Oceane.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}