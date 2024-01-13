package example.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class Item {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
}
