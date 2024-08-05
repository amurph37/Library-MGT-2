import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String libraryCardNumber;
    private List<Book> loanedBooks;

    public User(String name, String libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
        this.loanedBooks = new ArrayList<>();
    }

    // Getters and setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLibraryCardNumber() { return libraryCardNumber; }
    public void setLibraryCardNumber(String libraryCardNumber) { this.libraryCardNumber = libraryCardNumber; }

    public List<Book> getLoanedBooks() { return loanedBooks; }
    public void setLoanedBooks(List<Book> loanedBooks) { this.loanedBooks = loanedBooks; }

    public void loanBook(Book book) {
        loanedBooks.add(book);
    }

    public void returnBook(Book book) {
        loanedBooks.remove(book);
    }
}
