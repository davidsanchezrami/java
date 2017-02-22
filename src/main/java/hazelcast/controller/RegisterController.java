package hazelcast.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;

import hazelcast.model.User;
 
@Controller
@RequestMapping(value = "/register")
public class RegisterController {
	
	Config cfg = null;
	HazelcastInstance instance = null;
	
	public List<User> parsearUsuariosCSV() throws IOException{
		List<User> listadoUsuarios = new ArrayList<>();
		
		InputStream res = RegisterController.class.getResourceAsStream("/agenda.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(res));
		String line = null;
		//Saltamos la primera linea que es la cabecera
		line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] camposLinea = line.split(";");
			User u = new User(camposLinea[0], camposLinea[3], camposLinea[2], camposLinea[1]);
			listadoUsuarios.add(u);
		}
		reader.close();
		
		return listadoUsuarios;
	}
 
    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        User userForm = new User();    
        model.put("userForm", userForm);
        List<User> listadoUsuarios = null;
        
        try {
			listadoUsuarios = parsearUsuariosCSV();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
        IMap<String, User> customerMap = instance.getMap("users");
        
        if(listadoUsuarios != null && !listadoUsuarios.isEmpty()){
        	for(User u : listadoUsuarios){
        		customerMap.put(u.toString(), u);
        	}
        }
        
        /*for(int i=0; i<500000; i++){
        	customerMap.put("name"+i, new User("name"+i, "email"+i, "phone"+i, "surname"+i));
        }*/
         
        return "Index";
    }
     
    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userForm") User user,
            Map<String, Object> model) {
         
    	 IMap<String, User> customerMap = instance.getMap("users");
 
    	 User u = (User) model.get("userForm");
    	 
    	 String nameModel = u.getName();
    	 String emailModel = u.getEmail();
    	 String phoneModel = u.getPhone();
    	 String surnameModel = u.getSurname();
    	 String accion = u.getAccion();
    	 String foco = u.getFoco();
    	 
    	 if(foco.equals("1")){
    		 foco = "name";
    	 }else if(foco.equals("2")){
    		 foco = "email";
    	 }else if(foco.equals("3")){
    		 foco = "phone";
    	 }else{
    		 foco = "surname";
    	 }
    	 u.setFoco(foco);
    	 
    	 if(accion.equals("insertar")){
    		 List<User> userList = new ArrayList<>();
             model.put("users", userList);
             User userNuevo = new User(nameModel, emailModel, phoneModel, surnameModel);
             customerMap.put(userNuevo.toString(), userNuevo);
    	 }else{
    		 String condition = "";
        	 
        	 if(nameModel != null && !nameModel.equals("")){
        		 condition = condition + "name LIKE '"+nameModel+"%' AND ";
        	 }
        	 if(emailModel != null && !emailModel.equals("")){
        		 condition = condition + "email LIKE '"+emailModel+"%' AND ";
        	 }
        	 if(phoneModel != null && !phoneModel.equals("")){
        		 condition = condition + "phone LIKE '"+phoneModel+"%' AND ";
        	 }
        	 if(surnameModel != null && !surnameModel.equals("")){
        		 condition = condition + "surname LIKE '"+surnameModel+"%' AND ";
        	 }
        	 
        	 if(!condition.equals("")){
        		 condition = condition.substring(0, condition.length()-5);
        	 }
        	 
        	 
        	 
        	 Set<User> foundUsers = (Set<User>) customerMap.values( new SqlPredicate(condition) );
        	 
        	 List<User> userList = new ArrayList<>();
        	 
             userList.addAll(foundUsers);
             if(userList.size() > 50){
            	 model.put("users", userList.subList(0, 15));
             }else{
            	 model.put("users", userList);
             }
             
    	 }
    	 
    	 
    	 
    	
         
       
         
        return "Index";
    }
 
}
