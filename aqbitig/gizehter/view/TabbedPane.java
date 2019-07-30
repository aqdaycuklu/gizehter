package aqbitig.gizehter.view;

import java.awt.event.KeyEvent;
import javax.swing.JComponent;

/**
 *
 * @author aqdaycuklu
 */
public class TabbedPane extends javax.swing.JTabbedPane {

    public TabbedPane(aqbitig.gizehter.view.Info info, aqbitig.gizehter.view.Image image) {

        init(info, image);
    }

    private void init(aqbitig.gizehter.view.Info info, aqbitig.gizehter.view.Image image) {
        addTab("Info", null, info, "Info");
        addTab("Image", null, image, "Image");
    }

}
