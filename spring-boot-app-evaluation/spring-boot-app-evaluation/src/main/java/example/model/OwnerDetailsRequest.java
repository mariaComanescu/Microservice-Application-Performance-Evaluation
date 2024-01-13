package example.model;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@NonNull
public class OwnerDetailsRequest {

    private Integer id;
    private String firstName;
    private String lastName;
}
