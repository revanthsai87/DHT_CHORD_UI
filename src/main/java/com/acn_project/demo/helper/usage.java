package com.acn_project.demo.helper;
import org.springframework.ui.Model;
public class usage {
    public static String help(Model m) throws InterruptedException {
        m.addAttribute("working","mania");
      //  Thread.sleep(10000);
        return "crazy";
    }
}
