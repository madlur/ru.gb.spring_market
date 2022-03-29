package factories;

import document.Document;
import document.OtherDocument;

public class OtherManager extends Manager {

    public OtherManager() {
        super.department = "other";
    }


    @Override
    public Document createDocument() {
        return new OtherDocument();
    }
}
