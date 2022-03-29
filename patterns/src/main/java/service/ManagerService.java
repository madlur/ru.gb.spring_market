package service;

import factories.ServiceManager;

public interface ManagerService {
    ServiceManager getManagerByName(String name);
}
