package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public void removeItemByValue(final String regex) {
        /*
        try {
            this.repo.removeIf(book -> book.getAuthor().equals(regex) || book.getTitle().equals(regex));
        } catch (NumberFormatException numberFormatException) {
            this.repo.removeIf(book -> book.getSize().equals(Integer.parseInt(regex)));
        }*/
        for (Book book : retreiveAll()) {
            if (Integer.toString(book.getSize()).equals(regex) ||
                    book.getTitle().equals(regex) ||
                    book.getAuthor().equals(regex)) {
                logger.info("remove book completed: " + book);
                repo.remove(book);
            }
        }
    }
}
