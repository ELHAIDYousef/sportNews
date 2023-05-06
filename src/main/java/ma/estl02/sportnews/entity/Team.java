package ma.estl02.sportnews.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
//Resoulution d'un problem de serialization (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","FieldHandler"})
public class Team implements Serializable {
    @Id
    private String name ;
    private String logoUrl;

}
