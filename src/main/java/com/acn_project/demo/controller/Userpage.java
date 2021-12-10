package com.acn_project.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.acn_project.demo.helper.usage;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.acn_project.demo.model.user;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import static sun.security.ssl.SSLLogger.help;
import myapp.driver.Driver;
import java.util.*;
import java.lang.*;
import java.util.ArrayList;

@Controller
public class Userpage {
    @GetMapping("/")
    public  String index(Model m) throws InterruptedException {

       m.addAttribute("working","mava");
        String s=usage.help(m);
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model) {
         model.addAttribute("working","Hero");
        return "about";
    }
    @GetMapping("/dns")
    public  String dns(Model m){
        return "dns";
    }
    @PostMapping("/dns")
    public String reply_dns(@ModelAttribute("u") user u,RedirectAttributes redirectAttributes,Model m) throws InterruptedException {
       ArrayList<String> ans= myapp.driver.Driver.user(u.getDomain());
        String s=u.getDomain();
       if(ans.get(0).contains("No")){
           redirectAttributes.addFlashAttribute("message",null);
           redirectAttributes.addFlashAttribute("error",ans.get(0));
       }
       else if(ans.size()==2){
           if(ans.get(0).contains("roxy")){
               List<String> items = Arrays.asList(ans.get(0).split("\\/"));
               String t=items.get(0).substring(6);
               for(int i=1;i<items.size();i++){
                   t+=" and "+items.get(i);
               }
               if(ans.get(1).contains("ubdomai")){
                   redirectAttributes.addFlashAttribute("message",s);
                   redirectAttributes.addFlashAttribute("proxysub",t);
                redirectAttributes.addFlashAttribute("record","NS Record");
                redirectAttributes.addFlashAttribute("link",items.get(0).substring(6));
                redirectAttributes.addFlashAttribute("sub","There are Subdomains for this domain");
               }
               else{
                   redirectAttributes.addFlashAttribute("message",s);
                   redirectAttributes.addFlashAttribute("proxysub",t);
                   redirectAttributes.addFlashAttribute("record","NS Record");
                   redirectAttributes.addFlashAttribute("link",items.get(0).substring(6));
               }
           }
           else if(ans.get(1).contains("ubdomai")){
               redirectAttributes.addFlashAttribute("message",s);
               redirectAttributes.addFlashAttribute("proxysub",ans.get(0).substring(6));
               redirectAttributes.addFlashAttribute("sub","There are Subdomains for this domain");
               redirectAttributes.addFlashAttribute("record","NS Record");
               redirectAttributes.addFlashAttribute("link",ans.get(0).substring(6));
           }
           else if(ans.get(1).contains("NS")){
               redirectAttributes.addFlashAttribute("message",s);
               redirectAttributes.addFlashAttribute("proxysub",ans.get(0).substring(6));
               redirectAttributes.addFlashAttribute("record","NS Record");
               redirectAttributes.addFlashAttribute("link",ans.get(0).substring(6));
           }
         else if(ans.get(0).contains("ach")){
             redirectAttributes.addFlashAttribute("message",s);
             redirectAttributes.addFlashAttribute("proxysub",ans.get(1).substring(6));
             redirectAttributes.addFlashAttribute("record","Fetched from Cache");
             redirectAttributes.addFlashAttribute("link",ans.get(1).substring(6));
         }
        else if(ans.get(0).contains("MX")){
             redirectAttributes.addFlashAttribute("message",s);
             redirectAttributes.addFlashAttribute("proxysub",ans.get(1).substring(6));
             redirectAttributes.addFlashAttribute("record","MX Record");
             redirectAttributes.addFlashAttribute("link",ans.get(1).substring(6));
         }
        else{
            System.out.println("Not Handled");

           }
       }

       else if(ans.size()==3){
           if(ans.get(0).contains("roxy")){
               List<String> items = Arrays.asList(ans.get(0).split("\\/"));
               String t=items.get(0).substring(6);
               for(int i=1;i<items.size();i++){
                   t+=" and "+items.get(i);
               }

                   redirectAttributes.addFlashAttribute("message",ans.get(2).substring(ans.get(2).indexOf('>')+1));
                   redirectAttributes.addFlashAttribute("proxysub",t);
                   redirectAttributes.addFlashAttribute("record","Cname Record/NS Record");
                   redirectAttributes.addFlashAttribute("link",items.get(0).substring(6));
           }
           else{
               redirectAttributes.addFlashAttribute("message",ans.get(2).substring(ans.get(2).indexOf('>')+1));
               redirectAttributes.addFlashAttribute("proxysub",ans.get(0).substring(6));
               redirectAttributes.addFlashAttribute("record","Cname Record/NS Record");
               redirectAttributes.addFlashAttribute("link",ans.get(0).substring(6));
           }

       }

return "redirect:/dns";
    }
    @GetMapping("/contact")
    public String contact(Model m){
        return "contact";
    }
    @GetMapping("/app")
    public String app(Model m){
        return "app";
    }
    @PostMapping("/app")
    public String app_result(@ModelAttribute("u") user u,RedirectAttributes redirectAttributes){
        String s=myapp.driver.Driver.admin( Integer.parseInt(u.getA_1()),Integer.parseInt(u.getA_2()),Integer.parseInt(u.getA_3()));
        System.out.println(s);
        redirectAttributes.addFlashAttribute("app_return",s);

        return "redirect:/app";
    }
    @PostMapping("/result")
    public String result( @ModelAttribute("u") user u, Model m){
        System.out.println("hasda");
       String  s=u.getDomain();

        System.out.println("found value"+s);
        m.addAttribute("domain",s);
        return "result";
    }
}
