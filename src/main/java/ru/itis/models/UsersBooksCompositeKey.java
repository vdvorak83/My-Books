package ru.itis.models;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

@Embeddable
public class UsersBooksCompositeKey implements Serializable {
    private Integer userId;
    private Integer bookId;
}
