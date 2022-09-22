public class User {

    int idloaners;
    String firstname;
    String lastname;
    String books;
    String username;

    public User(int idloaners, String firstname, String lastname, String books, String username) {
        this.idloaners = idloaners;
        this.firstname = firstname;
        this.lastname = lastname;
        this.books = books;
        this.username = username;
    }

    public int getIdloaners() {
        return idloaners;
    }

    public void setIdloaners(int idloaners) {
        this.idloaners = idloaners;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User: " +
                "idloaners: " + idloaners +
                " firstname: " + firstname + '\'' +
                " lastname: " + lastname + '\'' +
                " books: " + books + '\'' +
                " username: " + username + '\'';
    }
}
