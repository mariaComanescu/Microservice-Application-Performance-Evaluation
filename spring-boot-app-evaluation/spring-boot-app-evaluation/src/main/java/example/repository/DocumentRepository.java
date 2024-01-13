package example.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import example.model.Item;
import example.model.OwnerDetailsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DocumentRepository {

    @Autowired
    private final ReactiveMongoTemplate template;

    @Autowired
    private ObjectMapper objectMapper;


    public Mono<Item> insertDocumentInMongoDB(Item item, OwnerDetailsRequest owner) {
        log.info("About to insert first item: {}", owner);
        item.setId(owner.getId());
        item.setFirstName(owner.getFirstName());
        item.setLastName(owner.getLastName());
        return template.insert(item);
    }

    public Mono<Item> retrieveDocumentFromMongoDB(Integer id) {
        log.info("About to get data for id: {}", id);
        return template.findById(id, Item.class);
    }

    public Mono<UpdateResult> updateDocumentInMongoDB(OwnerDetailsRequest request) {
        log.info("About to update data for id: {}", request.getId());
        Query query = new Query(new Criteria().andOperator(Criteria.where("_id").is(request.getId())));
        Update update = new Update();
        if (request.getFirstName() != null) {
            update.set("firstName", request.getFirstName());
        }
        if (request.getLastName() != null) {
            update.set("lastName", request.getLastName());
        }
        return template.updateFirst(query, update, Item.class);
    }
}
