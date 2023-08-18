package JavaApiManageFile.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @Column
    private String name;
    @Getter @Setter
    @Column
    private String type;
    @Getter @Setter
    @Column
    private String path;

    public Files() {
    }

    public Files(String name, String type, String path) {
        this.name = name;
        this.type = type;
        this.path = path;
    }
}
