package com.example.academiacx.services;

import com.example.academiacx.models.StreamingModel;
import com.example.academiacx.repository.StreamingRepository;
import com.example.academiacx.services.inter.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingServiceImpl implements StreamingService {

    @Autowired
    private StreamingRepository streamingRepository;

    @Override
    public List<StreamingModel> findAll() {
        return streamingRepository.findAll();
    }

    @Override
    public Optional<StreamingModel> findById(Long id) {
        return streamingRepository.findById(id);
    }

    @Override
    public StreamingModel create(StreamingModel streaming) {
        return streamingRepository.save(streaming);
    }


    @Override
    public void delete(Long id) {
        streamingRepository.deleteById(id);
    }
}

