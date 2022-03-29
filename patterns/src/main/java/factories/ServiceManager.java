package factories;

import document.Document;
import document.Order;

public class ServiceManager extends Manager {
    public ServiceManager(String name) {
        super.department = "service";
    }

    @Override
    public Document createDocument() {
        return new Order();
    }
}
