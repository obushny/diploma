package dbwriter.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lkmbases")
public class LkmBases {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer id;

    @Column (name = "bases")
    private String bases;

    @Column (name = "bases_name")
    private String basesName;

    @OneToMany(mappedBy="lkmBases",cascade=CascadeType.ALL)
    private Set<Lkm> lkm = new HashSet<Lkm>();

    protected  LkmBases() {};

    public   LkmBases(String base, String baseName) {
        this.bases=base;
        this.basesName=baseName;

    };



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBases() {
        return bases;
    }

    public void setBases(String bases) {
        this.bases = bases;
    }

    public String getBasesName() {
        return basesName;
    }

    public void setBasesName(String basesName) {
        this.basesName = basesName;
    }
}
