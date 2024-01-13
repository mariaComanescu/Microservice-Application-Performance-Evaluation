package example.resource;


import example.model.Item;
import example.model.OwnerDetailsRequest;
import example.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/operations")
@AllArgsConstructor
public class ItemOperationsResource {

    private DocumentRepository documentRepository;

    @GetMapping("/get/{id}")
    public Mono<Item> getData(@PathVariable Integer id) {
        return documentRepository.retrieveDocumentFromMongoDB(id);
    }

    @PostMapping("/insert")
    public Mono<Item> insertData(@RequestBody OwnerDetailsRequest request) {
        Item item = new Item();
        return documentRepository.insertDocumentInMongoDB(item, request);
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<String>> updateData(@RequestBody OwnerDetailsRequest request) {
        documentRepository.updateDocumentInMongoDB(request).subscribe();
        return Mono.just(new ResponseEntity<>(HttpStatus.ACCEPTED));
    }
}
