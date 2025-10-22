
package com.example.askIt.service;

import com.example.askIt.model.ApplicationRequest;
import com.example.askIt.repository.ApplicationRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationRequestService {

    private final ApplicationRequestRepository repository;

    public ApplicationRequestService(ApplicationRequestRepository repository) {
        this.repository = repository;
    }

    public List<ApplicationRequest> findAll() {
        return repository.findAll();
    }

    public List<ApplicationRequest> findByHandled(boolean handled) {
        return repository.findByHandled(handled);
    }

    public Optional<ApplicationRequest> findById(Long id) {
        return repository.findById(id);
    }

    public ApplicationRequest save(ApplicationRequest request) {
        return repository.save(request);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void setHandled(Long id, boolean handled) {
        repository.findById(id).ifPresent(r -> {
            r.setHandled(handled);
            repository.save(r);
        });
    }
}
