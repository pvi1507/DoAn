/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Repository.MonAnRepository;
import Model.MonAn;
import java.util.List;
/**
 *
 * @author DANG
 */
public class MonAnService {
    private MonAnRepository repo = new MonAnRepository();

    public List<MonAn> getAll() {
        return repo.getAll();
    }

    public boolean add(MonAn ma) {
        return repo.insert(ma);
    }

    public boolean update(MonAn ma) {
        return repo.update(ma);
    }

    public boolean delete(int maMon) {
        return repo.delete(maMon);
    }
    
}
