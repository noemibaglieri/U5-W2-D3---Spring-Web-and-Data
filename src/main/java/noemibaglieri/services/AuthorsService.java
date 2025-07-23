package noemibaglieri.services;

import lombok.extern.slf4j.Slf4j;
import noemibaglieri.entities.Author;
import noemibaglieri.exceptions.NotFoundException;
import noemibaglieri.payloads.NewAuthorPayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AuthorsService {
    private List<Author> authorsDB = new ArrayList<>();

    public List<Author> findAll() {
        return this.authorsDB;
    }

    public Author save(NewAuthorPayload payload) {
        Author newAuthor = new Author(payload.getFirstName(), payload.getLastName(), payload.getEmail(), payload.getDateOfBirth(), "https://ui-avatars.com/api/?name=Mario+Rossi");
        this.authorsDB.add(newAuthor);
        log.info("The author * " + newAuthor.getFirstName() + newAuthor.getLastName() + " * was successfully registered to the DB");
        return newAuthor;
    }

    public Author findById(long authorId) {
        Author found = null;
        for (Author author : this.authorsDB) {
            if(author.getId() == authorId) found = author;
        }

        if (found == null) throw new NotFoundException("Author with id * " + authorId + " * was not found");

        return found;
    }

    public Author findByIdAndUpdate(long authorId, NewAuthorPayload payload) {
        Author found = null;
        for (Author author : this.authorsDB) {
            if (author.getId() == authorId) {
                found = author;
                found.setFirstName(payload.getFirstName());
                found.setLastName(payload.getLastName());
                found.setEmail(payload.getEmail());
                found.setDateOfBirth(payload.getDateOfBirth());
            }
        }

        if(found == null) throw new NotFoundException("Author with id * " + authorId + " * was not found");
        return found;
    }

    public void findByIdAndDelete(long authorId) {
        Author found = null;
        for (Author author : this.authorsDB) {
            if(author.getId() == authorId) found = author;
        }
        if(found == null) throw new NotFoundException("Author with id * " + authorId + " * was not found");
        this.authorsDB.remove(found);
    }
}
