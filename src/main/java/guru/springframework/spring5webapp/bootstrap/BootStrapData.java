package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started!");
        Author a = new Author("firstName", "lastName");
        Book b = new Book("title", "200000");
        Publisher p = new Publisher("name", 3133);
        publisherRepository.save(p);

        b.setPublisher(p);
        p.getBooks().add(b);

        a.getBooks().add(b);
        b.getAuthors().add(a);

        bookRepository.save(b);
        authorRepository.save(a);
        publisherRepository.save(p);
        System.out.println("Data persisted");
        System.out.println("Count author: " + authorRepository.count());


    }
}
