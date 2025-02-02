package com.libraryManagementSystem.service.impl;

import com.libraryManagementSystem.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import com.libraryManagementSystem.models.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libraryManagementSystem.repositories.AuthorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author author) {
        log.info("Creating new author");
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        log.info("Getting all authors");
        List<Author> allAuthors = authorRepository.findAll();
        if (allAuthors.isEmpty()) {
            log.error("No authors found");
            throw new EntityNotFoundException("No authors found");
        }
        return allAuthors;
    }

    @Override
    public Author findAuthorById(long id) {
        log.info("Getting author with id {}", id);
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            log.error("Author with id {} is not found", id);
            throw new EntityNotFoundException("Author with id ".concat(String.valueOf(id)).concat( " is not found"));
        }
        log.info("Author with id {} found", id);
        return author.get();
    }

    @Override
    public Author updateAuthorBiography(long id, String biography) {
        log.info("Updating author biography");
        Author author= findAuthorById(id);
        author.setBiography(biography);
        return authorRepository.save(author);
    }

    @Override
    public String deleteAuthor(long id) {
        log.info("Deleting author with id {}", id);
        Author author= findAuthorById(id);
        authorRepository.delete(author);
        log.info("Author with id {} deleted", id);
        return "Author with id " .concat(String.valueOf(id)).concat( " deleted");
    }



}
