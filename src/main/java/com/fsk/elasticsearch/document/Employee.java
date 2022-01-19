package com.fsk.elasticsearch.document;

import com.fsk.elasticsearch.helper.Indicies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = Indicies.EMPLOYEE_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Employee {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String surname;

    @Field(type = FieldType.Text)
    private String identityNumber;

    @Field(type = FieldType.Text)
    private String department;

    @Field(type = FieldType.Text)
    private String city;

    @Field(type = FieldType.Text)
    private String country;
}
