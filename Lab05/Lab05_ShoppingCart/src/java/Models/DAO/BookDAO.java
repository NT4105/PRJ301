package Models.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Models.Entities.Book;


public class BookDAO {
    private static List<Book> bookList = null;
    public BookDAO() {
        bookList = new ArrayList<>();
        Book book1 = new Book("B001", "C#", 10.5);
        Book book2 = new Book("B002", "Java", 11.5);
        Book book3 = new Book("B003", "Spring", 12.5);
        Book book4 = new Book("Book4", "JSP", 13.5);
        Book book5 = new Book("Book5", "Ruby", 14.5);
        Collections.addAll(bookList,book1,book2,book3,book4,book5);
    }
    
        public List<Book> getBookList() {
            return bookList;
        }
        
        public Book getBookById(String id) {
            Book book = bookList.stream().filter(b -> b.getId().trim().
                    equals(id.trim())).findAny().orElse(null);
            return book;
        }
}
