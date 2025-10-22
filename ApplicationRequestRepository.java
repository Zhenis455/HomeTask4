
package com.example.askIt.repository;

import com.example.askIt.model.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
    List<ApplicationRequest> findByHandled(boolean handled);
}
