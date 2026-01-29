package repository.interfaces;

import entity.Service;
import java.util.List;

public interface IServiceRepository {
    boolean createService(Service service);
    List<Service> getAllServices();
}