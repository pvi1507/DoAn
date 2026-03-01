/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bc_trangad;
import Controller.LoginController;
import View.LoginView;
/**
 *
 * @author DANG
 */
public class BC_Trangad {

    public static void main(String[] args) {
        LoginView view = new LoginView();
        new LoginController(view);
        view.setVisible(true);
    }
}
