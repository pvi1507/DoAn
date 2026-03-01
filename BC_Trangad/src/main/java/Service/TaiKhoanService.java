/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.TaiKhoan;
import repository.TaiKhoanRepository;
/**
 *
 * @author DANG
 */
public class TaiKhoanService {
    private TaiKhoanRepository repo = new TaiKhoanRepository();

    public TaiKhoan login(String username, String password) {
        return repo.login(username, password);
    }
}
