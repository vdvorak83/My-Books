package ru.itis.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "file_info")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "storage_file_name")
    private String storageFileName;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    @Column(name = "size")
    private Long size;
}
