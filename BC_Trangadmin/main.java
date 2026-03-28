import Controller.DangNhapController;
import View.DangNhapView;

public class main {
    public static void main(String[] args) {

        DangNhapView view = new DangNhapView();
        new DangNhapController(view);
        view.setVisible(true);
    }
}