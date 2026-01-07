import java.util.Objects;

//
//@Getter
//@Setter
public class User {
    private int id;
    private  String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }


    // getters && setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) { return false;}
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
