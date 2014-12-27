package design.patterns.builder;

/**
 * Created by PATEL1 on 12/27/14.
 */
public class Hero {
    private String name;
    private final Profession profession;
    private HairType hairType;
    private HairColor hairColor;
    private Armor armor;
    private Weapon weapon;

    public Hero(HeroBuilder builder) {
        this.name = builder.name;
        this.profession = builder.profession;
        this.hairType = builder.hairType;
        this.hairColor = builder.hairColor;
        this.armor = builder.armor;
        this.weapon = builder.weapon;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( profession );
        builder.append( "named ");
        builder.append( name );
        if ( hairColor != null || hairType != null ){
            builder.append(" with ");
            if ( hairColor != null ){
                builder.append(hairColor).append(" ");
            }
            if ( hairType != null ){
                builder.append( hairType ).append(" ");
            }
            builder.append( hairType != HairType.BALD ? "hair" : "head" );
        }

        if ( armor != null ){
            builder.append(" wearing ").append( armor );
        }
        if ( weapon != null ){
            builder.append(" and wielding ").append( weapon );
        }
        builder.append(".");
        return builder.toString();
    }

    public static class HeroBuilder {
        private Profession profession;
        private String name;
        private HairColor hairColor;
        private HairType hairType;
        private Armor armor;
        private Weapon weapon;

        public HeroBuilder(Profession profession, String name){
            if ( profession == null || name == null ){
                throw new NullPointerException("profession and name can not be null");
            }
            this.profession = profession;
            this.name = name;
        }

        public HeroBuilder withHairType(HairType hairType){
            this.hairType = hairType;
            return this;
        }

        public HeroBuilder withHairColor( HairColor hairColor ){
            this.hairColor = hairColor;
            return this;
        }

        public HeroBuilder withWeapon( Weapon weapon ){
            this.weapon = weapon;
            return this;
        }

        public HeroBuilder withArmor( Armor armor ){
            this.armor = armor;
            return this;
        }

        public Hero build(){
            return new Hero(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profession getProfession() {
        return profession;
    }

    public HairType getHairType() {
        return hairType;
    }

    public void setHairType(HairType hairType) {
        this.hairType = hairType;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
