package com.example.url.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "urls")
@NoArgsConstructor
public class UrlTableEntry {

//            +----------------+---------------+------+-----+-------------------+-------------------+
//            | Field          | Type          | Null | Key | Default           | Extra             |
//            +----------------+---------------+------+-----+-------------------+-------------------+
//            | ID             | int           | NO   | PRI | NULL              | auto_increment    |
//            | LongURL        | varchar(2048) | NO   |     | NULL              |                   |
//            | ShortURL       | varchar(255)  | NO   | UNI | NULL              |                   |
//            | CreationDate   | datetime      | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
//            | ExpirationDate | datetime      | YES  |     | NULL              |                   |
//            | ClickCount     | int           | NO   |     | 0                 |                   |
//            | IsActive       | tinyint(1)    | NO   |     | 1                 |                   |
//            +----------------+---------------+------+-----+-------------------+-------------------+

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LongURL")
    private String longURL;

    @Column(name = "ShortURL")
    private String shortURL;

    @Column(name = "ExpirationDate")
    private java.sql.Date ExpirationDate;

    @Column(name = "ClickCount")
    private Integer clickCount;

    @Column(name = "IsActive")
    private Boolean isActive;

    public UrlTableEntry(String longURL, String shortURL, int expireAfterDays) {
        this.longURL = longURL;
        this.shortURL = shortURL;
        this.ExpirationDate = getExpirationDate(new Date(), expireAfterDays);
        this.clickCount = 0;
        this.isActive = true;
    }


    public java.sql.Date getExpirationDate(Date date, int expireAfterDays){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, expireAfterDays);
        return new java.sql.Date(c.getTimeInMillis());
    }
}
