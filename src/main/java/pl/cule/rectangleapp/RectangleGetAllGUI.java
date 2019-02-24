package pl.cule.rectangleapp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("getAll")
public class RectangleGetAllGUI extends VerticalLayout {

    private RectangleRepo rectangleRepo;

    private Label labelTitle;
    private TextArea textAreaRectangle;

    private Button buttonAll;

    @Autowired
    public RectangleGetAllGUI(RectangleRepo rectangleRepo) {
        this.rectangleRepo = rectangleRepo;
        labelTitle = new Label("Wypisz wszystkie prostokąty");
        buttonAll = new Button("Pokaż");
        textAreaRectangle = new TextArea("Wynik:");
        setSpacing(false);

        textAreaRectangle.setWidth("240px");

        buttonAll.addClickListener(event -> textAreaRectangle.setValue(String.valueOf(rectangleRepo.getAllRectangles())));

        add(labelTitle, buttonAll, textAreaRectangle);
    }
}
