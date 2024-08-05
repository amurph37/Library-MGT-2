import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Remove a book from the library by title
    public void removeBook(String title) {
        Predicate<Book> byTitle = book -> book.getTitle().equalsIgnoreCase(title);
        books.removeIf(byTitle);
    }

    // Find all books published in a specific year
    public List<Book> findBooksByYear(int year) {
        Predicate<Book> byYear = book -> book.getPublicationYear() == year;
        return books.stream().filter(byYear).collect(Collectors.toList());
    }

    // Find all books by a specific author
    public List<Book> findBooksByAuthor(String author) {
        Predicate<Book> byAuthor = book -> book.getAuthor().equalsIgnoreCase(author);
        return books.stream().filter(byAuthor).collect(Collectors.toList());
    }

    // Find the book with the most pages
    public Optional<Book> findBookWithMostPages() {
        return books.stream().max(Comparator.comparingInt(Book::getPages));
    }

    // Find all books with more than n pages
    public List<Book> findBooksWithMoreThanNPages(int n) {
        Predicate<Book> moreThanNPages = book -> book.getPages() > n;
        return books.stream().filter(moreThanNPages).collect(Collectors.toList());
    }

    // Print all book titles in the library, sorted alphabetically
    public void printAllBookTitles() {
        Function<Book, String> getTitle = Book::getTitle;
        Consumer<String> printTitle = System.out::println;
        books.stream().map(getTitle).sorted().forEach(printTitle);
    }

    // Find all books in a specific category
    public List<Book> findBooksByCategory(String category) {
        Predicate<Book> byCategory = book -> book.getCategory().equalsIgnoreCase(category);
        return books.stream().filter(byCategory).collect(Collectors.toList());
    }

    // Loan out a book
    public boolean loanBook(String title) {
        Predicate<Book> byTitle = book -> book.getTitle().equalsIgnoreCase(title) && !book.isOnLoan();
        Optional<Book> bookOpt = books.stream().filter(byTitle).findFirst();
        bookOpt.ifPresent(book -> {
            book.setOnLoan(true);
            book.setLoanDate(LocalDate.now());
        });
        return bookOpt.isPresent();
    }

    // Return a book
    public boolean returnBook(String title) {
        Predicate<Book> byTitle = book -> book.getTitle().equalsIgnoreCase(title) && book.isOnLoan();
        Optional<Book> bookOpt = books.stream().filter(byTitle).findFirst();
        bookOpt.ifPresent(book -> {
            book.setOnLoan(false);
            book.setLoanDate(null);
        });
        return bookOpt.isPresent();
    }

    // Calculate late fees
    public double calculateLateFees(String title) {
        Predicate<Book> byTitle = book -> book.getTitle().equalsIgnoreCase(title) && book.isOnLoan();
        Optional<Book> bookOpt = books.stream().filter(byTitle).findFirst();
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            long daysOnLoan = LocalDate.now().toEpochDay() - book.getLoanDate().toEpochDay();
            if (daysOnLoan > 14) {
                return (daysOnLoan - 14) * 0.5; // Assuming a fee of $0.50 per day
            }
        }
        return 0;
    }
}
