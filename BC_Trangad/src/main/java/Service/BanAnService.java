/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Model.BanAn;
import Repository.BanAnRepository;

import java.util.List;
/**
 *
 * @author DANG
 */
public class BanAnService {
     private BanAnRepository repo = new BanAnRepository();

    public List<BanAn> getAll() {
        return repo.getAll();
    }

    public boolean add(BanAn b) {
        return repo.insert(b);
    }

    public boolean update(BanAn b) {
        return repo.update(b);
    }

    public boolean delete(int maBan) {
        return repo.delete(maBan);
    }
}
