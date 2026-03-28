package Service;

import Model.TaiKhoan;
import Repository.TaiKhoanRepository;

public class TaiKhoanService {

    private TaiKhoanRepository repo = new TaiKhoanRepository();

    public TaiKhoan login(String username, String password) {
        return repo.login(username, password);
    }
}