package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


//serves as in memory data, mostly for practice, uses h2 memory database
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
        System.out.println("Started in the Bootsrap directery");

        Publisher publisher = new Publisher("Publisher name", "address line one", "salana", "Kansas", "32656");
        publisherRepository.save(publisher);

        Author tim = new Author("Timothy", "Evans");
        Book aBook = new Book("This is a book title", "112365");
        tim.getBooks().add(aBook);
        aBook.getAuthors().add(tim);

        aBook.setPublisher(publisher);
        publisher.getBooks().add(aBook);

        authorRepository.save(tim);
        bookRepository.save(aBook);
        publisherRepository.save(publisher);

        Author ben = new Author("Ben", "John");
        Book noway = new Book("this is another title", "123456");
        ben.getBooks().add(noway);
        noway.getAuthors().add(ben);
        noway.setPublisher(publisher);
        publisher.getBooks().add(noway);

        authorRepository.save(ben);
        bookRepository.save(noway);
        publisherRepository.save(publisher);


        System.out.println("Number of books: " + bookRepository.count());




        System.out.println("Publisher count: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());


    }
}
