package pl.cule.rectangleapp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("addNew")
public class RectangleGUI extends VerticalLayout {

    private RectangleRepo rectangleRepo;

    private Label labelTitle;
    private HorizontalLayout horizontalLayout;
    private TextField textFieldHeight;
    private TextField textFieldWidth;
    private Button button;
    private Dialog dialog;
    private Label labelDialog;

    @Autowired
    public RectangleGUI(RectangleRepo rectangleRepo) {
        this.rectangleRepo = rectangleRepo;
        initComponent();
        button.addClickListener(event -> addRectangle());
    }

    private void initComponent() {
        labelTitle = new Label("Dodaj nowy prostokąt");
        horizontalLayout = new HorizontalLayout();
        textFieldHeight = new TextField("Wysokość:");
        textFieldWidth = new TextField("Szerokość:");
        button = new Button("Dodaj");
        dialog = new Dialog();
        labelDialog = new Label();

        horizontalLayout.add(textFieldHeight, textFieldWidth);
        add(labelTitle, horizontalLayout, button);
    }

    public void addRectangle() {
        Rectangle rectangle = new Rectangle();
        try {
            rectangle.setHeight(Integer.parseInt(textFieldHeight.getValue()));
            rectangle.setWidth(Integer.parseInt(textFieldWidth.getValue()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (rectangle.getHeight() > 0 && rectangle.getWidth() > 0) {
            rectangle.setCircuit(rectangle.calculateCircuit(rectangle.getHeight(), rectangle.getWidth()));
            rectangle.setArea(rectangle.calculateArea(rectangle.getHeight(), rectangle.getWidth()));
            rectangleRepo.save(rectangle);
            labelDialog.setText("Dodano prostokąt: [" + rectangle.getHeight() + ", " + rectangle.getWidth() + "]");
        } else {
            labelDialog.setText("Niepoprawne dane!");
        }
        dialog.add(labelDialog);
        dialog.open();
    }
}
