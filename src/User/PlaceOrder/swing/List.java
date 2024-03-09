/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User.PlaceOrder.swing;

import User.PlaceOrder.component.PanelDetail;
import User.PlaceOrder.model.Model_Data;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import User.PlaceOrder.swing.List;

/**
 *
 * @author Zaky
 * @param <E>
 */
public class List<E extends Object> extends JList<E> {

    private final DefaultListModel<E> model;

    public List() {
        model = new DefaultListModel();
        setModel(model);
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean Selected, boolean focus) {
                Model_Data data;
                if (o instanceof Model_Data model_Data) {
                    data = model_Data;
                } else {
                    data = new Model_Data(false, o + "");
                }
                Item item = new Item(data);
                return item;
            }

        };
    }

    public void clearItems() {
        model.clear();
    }

    public void addItem(E element) {
        model.addElement(element);
    }
}
