package pl.cule.rectangleapp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("showById")
public class RectangleByIdGUI extends VerticalLayout {

    private RectangleRepo rectangleRepo;

    private Label labelTitle;

    private TextField textFieldSize;
    private Button buttonShow;

    private TextArea textAreaRectangle;

    @Autowired
    public RectangleByIdGUI(RectangleRepo rectangleRepo) {
        this.rectangleRepo = rectangleRepo;
        labelTitle = new Label("Wypisz prostokąty z danym ID");
        textFieldSize = new TextField("Podaj ID:");
        buttonShow = new Button("Pokaż");
        textAreaRectangle = new TextArea("Wynik:");
        setSpacing(false);

        textFieldSize.setWidth("100px");
        textAreaRectangle.setWidth("220px");

        buttonShow.addClickListener(event -> textAreaRectangle.setValue(rectangleRepo.getRectangleById(Long.parseLong(textFieldSize.getValue())).toString()));

        add(labelTitle, textFieldSize, buttonShow, textAreaRectangle);
    }
}
