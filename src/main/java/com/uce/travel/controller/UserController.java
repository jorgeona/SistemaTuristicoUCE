package com.uce.travel.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uce.travel.entity.Cliente;
import com.uce.travel.model.Usuario;
import com.uce.travel.repository.ClienteCrud;
import com.uce.travel.service.UserService;
//--------------------------------------
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller

public class UserController {

 @Autowired
 private UserService userService;
 
 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("user/login");
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
 public ModelAndView signup() {
  ModelAndView model = new ModelAndView();
  Usuario user = new Usuario();
  model.addObject("user", user);
  model.setViewName("user/signup");
  
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
 public ModelAndView createUser(@Valid Usuario user, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  Usuario userExists = userService.findUserByEmail(user.getEmail());
  
  if(userExists != null) {
   bindingResult.rejectValue("email", "error.user", "¡Ya existe Este email!");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("user/signup");
  } else {
   userService.saveUser(user);
   model.addObject("msg", "¡El usuario ha sido registrado con éxito!");
   model.addObject("user", new Usuario());
   model.setViewName("user/signup");
  }
  
  return model;
 }
 
 @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
 public ModelAndView home() {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  Usuario user = userService.findUserByEmail(auth.getName());
  
  model.addObject("userName", user.getFirstname() + " " + user.getLastname());
  model.setViewName("home/home");
  return model;
 }
 
 private final ClienteCrud clienteCrud;

 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }

@Autowired
 public UserController(ClienteCrud clienteCrud) {
     this.clienteCrud = clienteCrud;
 }
 
 @RequestMapping(value="/listar", method = RequestMethod.GET)
 public String listaClientes(ModelMap mp){
     mp.put("clientes", clienteCrud.findAll());
     return "index";
 }

//Listar Empleados
 @RequestMapping(value="/listar1", method = RequestMethod.GET)
 public String listaClientes1(ModelMap mp){
     mp.put("clientes", clienteCrud.findAll());
     return "empleados";
 }
 
 //Listar Pagos
 @RequestMapping(value="/pago", method = RequestMethod.GET)
 public String PagoClientes(ModelMap mp){
     mp.put("clientes", clienteCrud.findAll());
     return "pagos";
 }
 
 //-----

 @GetMapping("/entrar")
 public String showSignUpForm(Cliente cliente) {
     return "add-user";
 }
 
 @PostMapping("/adduser")
 public String addUser(@Valid Cliente cliente, BindingResult result, Model model) {
     if (result.hasErrors()) {
         return "add-user";
     }
     
     clienteCrud.save(cliente);
     model.addAttribute("clientes", clienteCrud.findAll());
     return "index";
 }
 
 @GetMapping("/edit/{id}")
 public String showUpdateForm(@PathVariable("id") long id, Model model) {
     Cliente cliente = clienteCrud.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de cliente invalido:" + id));
     model.addAttribute("cliente", cliente);
     return "update-user";
 }
 
 @PostMapping("/update/{id}")
 public String updateUser(@PathVariable("id") long id, @Valid Cliente cliente, BindingResult result, Model model) {
     if (result.hasErrors()) {
         cliente.setId(id);
         return "update-user";
     }
     
     clienteCrud.save(cliente);
     model.addAttribute("clientes", clienteCrud.findAll());
     return "index";
     
 }
 
 @GetMapping("/delete/{id}")
 public String deleteUser(@PathVariable("id") long id, Model model) {
     Cliente cliente = clienteCrud.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de cliente invalido:" + id));
     clienteCrud.delete(cliente);
     model.addAttribute("clientes", clienteCrud.findAll());
     return "index";
 }
 
 //----controlador actualiza pagos
 
 @GetMapping("/edit1/{id}")
 public String showUpdateForm1(@PathVariable("id") long id, Model model) {
     Cliente cliente = clienteCrud.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de cliente invalido:" + id));
     model.addAttribute("cliente", cliente);
     return "actualiza-pago";
 }
 
 @PostMapping("/update1/{id}")
 public String updateUser1(@PathVariable("id") long id, @Valid Cliente cliente, BindingResult result, Model model) {
     if (result.hasErrors()) {
         cliente.setId(id);
         return "update-user";
     }
     
     clienteCrud.save(cliente);
     model.addAttribute("clientes", clienteCrud.findAll());
     return "pagos";
 }
 
 
 //----REPORTES-----
 //-------------------------REPORTE DETALLE CLIENTE
 @Autowired
 private DataSource dataSource;
 @RequestMapping("/report")
 @PostMapping
 public void imprimir(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException
                                                                                                       , SQLException
                                                                                                       , IOException {
     parameters = parameters == null ? parameters = new HashMap<>() : parameters;

     // Pega o arquivo .jasper localizado em resources
     InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteDetalleCliente.jasper");

     // Cria o objeto JaperReport com o Stream do arquivo jasper
     JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
     // Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

     // Configura a respota para o tipo PDF
     response.setContentType("application/pdf");
     // Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
     // para fazer download do relatório troque 'inline' por 'attachment'
     response.setHeader("Content-Disposition", "inline; filename=ReporteDetalleCliente.pdf");

     // Faz a exportação do relatório para o HttpServletResponse
     final OutputStream outputStream = response.getOutputStream();
     JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
 }
@RequestMapping("/report1")
@PostMapping
public void imprimir1(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteEneroAbril.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteEneroAbril.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
}
@RequestMapping("/report2")
@PostMapping
public void imprimir2(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteMayoAgosto.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteMayoAgosto.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
}
@RequestMapping("/report3")
@PostMapping
public void imprimir3(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteNCliente.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteNCliente.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
}
@RequestMapping("/report4")
@PostMapping
public void imprimir4(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteSeptiembreDiciembre.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteSeptiembreDiciembre.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
}
@RequestMapping("/report5")
@PostMapping
public void imprimir5(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteReservasCliente.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteReservasCliente.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
} 
@RequestMapping("/report6")
@PostMapping
public void imprimir6(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteValoresCliente.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteValoresCliente.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
} 
@RequestMapping("/report7")
@PostMapping
public void imprimir7(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteGraficoReservasCliente.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteGraficoReservasCliente.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
} 
@RequestMapping("/report8")
@PostMapping
public void imprimir8(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteGraficoNClientePais.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteGraficoNClientePais.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
}
@RequestMapping("/report9")
@PostMapping
public void imprimir9(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteGraficaTipoCliente.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteGraficaTipoCliente.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
}
@RequestMapping("/report10")
@PostMapping
public void imprimir10(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws JRException, SQLException, IOException {
    parameters = parameters == null ? parameters = new HashMap<>() : parameters;
    InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/ReporteFinalValoresCliente.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=ReporteFinalValoresCliente.pdf");
    final OutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
}
}
