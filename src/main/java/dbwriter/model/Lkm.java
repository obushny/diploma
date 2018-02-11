package dbwriter.model;

import javax.persistence.*;

@Entity
@Table(name="lkm")
public class Lkm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer id;

    @Column (name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "base_id", nullable = false)
    private LkmBases lkmBases = new LkmBases();

    @ManyToOne
    @JoinColumn(name = "prefix_id", nullable = true)
    private Lkmprefix lkmprefix = new Lkmprefix();

    @Column (name = "short_description")
    private String shortDescription;

    @Column (name = "description")
    private String description;

    @Column (name = "destination")
    private String destination;

    @Column (name = "colors")
    private String colors;

    @Column (name = "use_unstruction")
    private String useInstruction;

    @Column (name = "layers")
    private String layers;

    @Column (name = "spreading_capasity")
    private String spreadingCapasity;

    @Column (name = "layer_thickness")
    private String layerThickness;

    @Column (name = "ductility_thickness")
    private String ductility_thickness;

    @Column (name = "dryingtime")
    private String dryingtime;

    @Column (name = "hardness")
    private String hardness;

    @Column (name = "charge")
    private String charge;

    @Column (name = "solids")
    private String solids;

    protected Lkm(){};

    public Lkm(String name, LkmBases lkmBases, Lkmprefix lkmprefix, String shortDescription, String description,
               String destination, String colors, String useInstruction, String layers, String spreadingCapasity,
               String layerThickness, String ductility_thickness, String dryingtime, String hardness, String charge,
               String solids) {
        this.name = name;
        this.lkmBases = lkmBases;
        this.lkmprefix = lkmprefix;
        this.shortDescription = shortDescription;
        this.description = description;
        this.destination = destination;
        this.colors = colors;
        this.useInstruction = useInstruction;
        this.layers = layers;
        this.spreadingCapasity = spreadingCapasity;
        this.layerThickness = layerThickness;
        this.ductility_thickness = ductility_thickness;
        this.dryingtime = dryingtime;
        this.hardness = hardness;
        this.charge = charge;
        this.solids = solids;
    }

    public Lkm(String id, String name, LkmBases lkmBases, Lkmprefix lkmprefix, String shortDescription, String description,
               String destination, String colors, String useInstruction, String layers, String spreadingCapasity,
               String layerThickness, String ductility_thickness, String dryingtime, String hardness, String charge,
               String solids) {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.lkmBases = lkmBases;
        this.lkmprefix = lkmprefix;
        this.shortDescription = shortDescription;
        this.description = description;
        this.destination = destination;
        this.colors = colors;
        this.useInstruction = useInstruction;
        this.layers = layers;
        this.spreadingCapasity = spreadingCapasity;
        this.layerThickness = layerThickness;
        this.ductility_thickness = ductility_thickness;
        this.dryingtime = dryingtime;
        this.hardness = hardness;
        this.charge = charge;
        this.solids = solids;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy="lkmBases",cascade=CascadeType.ALL)
    public LkmBases getLkmBases() {
        return lkmBases;
    }

    public void setLkmBases(LkmBases lkmBases) {
        this.lkmBases = lkmBases;
    }

    public Lkmprefix getLkmprefix() {
        return lkmprefix;
    }

    public void setLkmprefix(Lkmprefix lkmprefix) {
        this.lkmprefix = lkmprefix;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getUseInstruction() {
        return useInstruction;
    }

    public void setUseInstruction(String useInstruction) {
        this.useInstruction = useInstruction;
    }

    public String getLayers() {
        return layers;
    }

    public void setLayers(String layers) {
        this.layers = layers;
    }

    public String getSpreadingCapasity() {
        return spreadingCapasity;
    }

    public void setSpreadingCapasity(String spreadingCapasity) {
        this.spreadingCapasity = spreadingCapasity;
    }

    public String getLayerThickness() {
        return layerThickness;
    }

    public void setLayerThickness(String layerThickness) {
        this.layerThickness = layerThickness;
    }

    public String getDuctility_thickness() {
        return ductility_thickness;
    }

    public void setDuctility_thickness(String ductility_thickness) {
        this.ductility_thickness = ductility_thickness;
    }

    public String getDryingtime() {
        return dryingtime;
    }

    public void setDryingtime(String dryingtime) {
        this.dryingtime = dryingtime;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getSolids() {
        return solids;
    }

    public void setSolids(String solids) {
        this.solids = solids;
    }
}
