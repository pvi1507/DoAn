/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Model.LoaiMon;
import Repository.LoaiMonRepository;
import java.util.List;
/**
 *
 * @author DANG
 */
public class LoaiMonService {
    private LoaiMonRepository repo = new LoaiMonRepository();

    public List<LoaiMon> getAll() {
        return repo.selectAll();
    }

    public boolean add(LoaiMon lm) {
        return repo.insert(lm);
    }

    public boolean update(LoaiMon lm) {
        return repo.update(lm);
    }

    public boolean delete(int maLoai) {
        return repo.delete(maLoai);
    }

    public List<LoaiMon> search(String ten) {
        return repo.search(ten);
    }
}
