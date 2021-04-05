package com.service;

import com.dao.ReaderRepository;
import com.entity.Book;
import com.entity.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReaderService {
    @Autowired
    public ReaderRepository rep;

    public Page<Reader> getReaderList(int page, int size){
        PageRequest request = PageRequest.of(page, size);
        return rep.findAll(request);
    }
    public List<Reader> getReaderList(){
        return rep.findAll();
    }
    public void save(Reader reader) {
        rep.save(reader);
    }
    public void delete(Long id){
        rep.deleteById(id);
    }

    public Reader findById(Long id) {
        return rep.findById(id).get();
    }
}
