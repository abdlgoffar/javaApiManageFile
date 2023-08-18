package JavaApiManageFile.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Images {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter
    @Column
    private String name;
    @Getter @Setter
    @Column
    private String type;
    @Getter @Setter
    @Lob
    @Column(length = 1000)
    private byte[] file;

    public Images() {
    }

    public Images(String name, String type, byte[] file) {
        this.name = name;
        this.type = type;
        this.file = file;
    }
}
