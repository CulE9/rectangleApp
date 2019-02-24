package pl.cule.rectangleapp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("getBySize")
public class RectangleSizeGUI extends VerticalLayout {

    private RectangleRepo rectangleRepo;

    private Label labelTitle;

    private TextField textFieldSize;
    private TextArea textAreaRectangle;

    private HorizontalLayout horizontalLayout;
    private Button buttonBigger;
    private Button buttonSmaller;

    @Autowired
    public RectangleSizeGUI(RectangleRepo rectangleRepo) {
        this.rectangleRepo = rectangleRepo;
        labelTitle = new Label("Wypisz prostokąty");
        textFieldSize = new TextField("Podaj obwód:");
        textAreaRectangle = new TextArea("Wynik:");
        horizontalLayout = new HorizontalLayout();
        buttonBigger = new Button("Pokaż większe");
        buttonSmaller = new Button("Pokaż mniejsze");
        setSpacing(false);

        textFieldSize.setWidth("100px");
        textAreaRectangle.setWidth("220px");

        buttonBigger.addClickListener(event -> textAreaRectangle.setValue(rectangleRepo.getBigRectangles(Integer.parseInt(textFieldSize.getValue())).toString()));
        buttonSmaller.addClickListener(event -> textAreaRectangle.setValue(rectangleRepo.getSmallRectangles(Integer.parseInt(textFieldSize.getValue())).toString()));

        horizontalLayout.add(buttonBigger, buttonSmaller);

        add(labelTitle, textFieldSize, horizontalLayout, textAreaRectangle);
    }
}
