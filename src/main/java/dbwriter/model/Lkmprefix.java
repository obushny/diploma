package dbwriter.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="lkmprefix")
public class Lkmprefix {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column (name = "prifix")
    private String prefix;

    @Column (name = "prifix_name")
    private String prefixName;

    @OneToMany(mappedBy="lkmprefix",cascade=CascadeType.ALL)
    private Set<Lkm> lkm = new HashSet<Lkm>();

    protected  Lkmprefix() {};

    public   Lkmprefix(String prefix, String prefixName) {
        this.prefix=prefix;
        this.prefixName=prefixName;

    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }
}



