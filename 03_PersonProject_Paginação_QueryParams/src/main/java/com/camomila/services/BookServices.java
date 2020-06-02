package com.camomila.services;

import com.camomila.converter.DozerConverter;
import com.camomila.data.model.Book;
import com.camomila.data.vo.v1.BookVO;
import com.camomila.data.vo.v1.PersonVO;
import com.camomila.exception.ResourceNotFoundException;
import com.camomila.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    BookRepository repository;

    public BookVO create(BookVO book) {
        var entity = DozerConverter.parseObject(book, Book.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public BookVO update(BookVO book) {
        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity .setTitle(book.getTitle());
        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        repository.delete(entity);
    }

    public BookVO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public Page<BookVO> findBookByTitle(String title, Pageable pageable) {
        var page = repository.findBookByTitle(title, pageable);
        return page.map(this::convertToBookVO);
    }

    public Page<BookVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertToBookVO);
    }

    private BookVO convertToBookVO(Book book) {
        return DozerConverter.parseObject(book, BookVO.class);
    }
}
