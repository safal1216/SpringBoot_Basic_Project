package com.example.newproject.entity;
import com.example.newproject.enums.Sentiment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "journal_entity")
@Data
@NoArgsConstructor
public class JournalEntity {
    @Id
    private ObjectId id;

    private String detail;
    private String title;
    private LocalDateTime date;
    private Sentiment sentiment;
}
