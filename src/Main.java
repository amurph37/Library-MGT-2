import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addBook(new Book("Book One", "Author A", 2020, 300, "Fiction"));
        library.addBook(new Book("Book Two", "Author B", 2019, 150, "Non-Fiction"));
        library.addBook(new Book("Book Three", "Author A", 2021, 500, "Science"));
        library.addBook(new Book("Book Four", "Author C", 2018, 200, "History"));

        // Finding books by author
        List<Book> authorABooks = library.findBooksByAuthor("Author A");
        System.out.println("Books by Author A:");
        authorABooks.forEach(book -> System.out.println(book.getTitle()));

        // Loaning a book
        library.loanBook("Book One");

        // Calculating late fees
        double lateFees = library.calculateLateFees("Book One");
        System.out.println("Late fees for 'Book One': " + lateFees);

        // Returning a book
        library.returnBook("Book One");

        // Printing all book titles
        System.out.println("All book titles:");
        library.printAllBookTitles();
    }
}
