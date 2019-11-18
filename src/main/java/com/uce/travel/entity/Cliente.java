package com.uce.travel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.validation.constraints.NotBlank;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String contrato;
    private String procedencia;   
    private String name;
    private String ncelular;
    private String nfijo;
    private String email;
    private String nacionalidad;
    private int edad;
    private String tipo;
    private String fnac;
    private String fini;
    private String ffin;
    private String cuantia;
    private int cuotaunica;
    private int valorcontrato;
    private int valordia;
    private int saldo;
    private String cortesia;
    private String dcortesia;
    private String cortesiaen;
    private String cotizaciones;
    private String reservas;
    private String dreservas;
    private String freserva;
//------Campos empleado
    private String nombrempleado;
    private String fechadigitacion;
    
//-------Campos Pagos
    private int pago1;
    private String fecha1;
    private int pago2;
    private String fecha2;
    private int pago3;
    private String fecha3;
    private int pago4;
    private String fecha4;
    private int pago5;
    private String fecha5;
    private int pago6;
    private String fecha6;
    private int pago7;
    private String fecha7;
    private int pago8;
    private String fecha8;
    private int pago9;
    private String fecha9;
    private int pago10;
    private String fecha10;
    
    
    public Cliente() {}
          
    public Cliente(String contrato, String procedencia, String name, String ncelular, String nfijo, String email,
			String nacionalidad, int edad, String tipo, String fnac, String fini, String ffin, String cuantia,
			int cuotaunica, int valorcontrato, int valordia, int saldo, String cortesia, String dcortesia, 
			String cortesiaen, String cotizaciones, String reservas, String dreservas, String freserva,String nombrempleado,String fechadigitacion) {
		super();
		this.contrato = contrato;
		this.procedencia = procedencia;
		this.name = name;
		this.ncelular = ncelular;
		this.nfijo = nfijo;
		this.email = email;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.tipo = tipo;
		this.fnac = fnac;
		this.fini = fini;
		this.ffin = ffin;
		this.cuantia = cuantia;
		this.cuotaunica = cuotaunica;
		this.valorcontrato = valorcontrato;
		this.valordia = valordia;
		this.saldo = saldo;
		this.cortesia = cortesia;
		this.dcortesia = dcortesia;
		this.cortesiaen = cortesiaen;
		this.cotizaciones = cotizaciones;
		this.reservas = reservas;
		this.reservas = dreservas;
		this.freserva = freserva;
		this.nombrempleado = nombrempleado;
		this.fechadigitacion = fechadigitacion;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNcelular() {
		return ncelular;
	}
	public void setNcelular(String ncelular) {
		this.ncelular = ncelular;
	}
	public String getNfijo() {
		return nfijo;
	}
	public void setNfijo(String nfijo) {
		this.nfijo = nfijo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public int getEdad() {
		return edad;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFnac() {
		return fnac;
	}

	public void setFnac(String fnac) {
		this.fnac = fnac;
	}

	public String getFini() {
		return fini;
	}

	public void setFini(String fini) {
		this.fini = fini;
	}

	public String getFfin() {
		return ffin;
	}

	public void setFfin(String ffin) {
		this.ffin = ffin;
	}

	public String getCuantia() {
		return cuantia;
	}

	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}

	public int getCuotaunica() {
		return cuotaunica;
	}

	public void setCuotaunica(int cuotaunica) {
		this.cuotaunica = cuotaunica;
	}

	public int getValorcontrato() {
		return valorcontrato;
	}

	public void setValorcontrato(int valorcontrato) {
		this.valorcontrato = valorcontrato;
	}

	public int getValordia() {
		return valordia;
	}

	public void setValordia(int valordia) {
		this.valordia = valordia;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getCortesia() {
		return cortesia;
	}

	public void setCortesia(String cortesia) {
		this.cortesia = cortesia;
	}

	public String getDcortesia() {
		return dcortesia;
	}

	public void setDcortesia(String dcortesia) {
		this.dcortesia = dcortesia;
	}
	
	public String getCortesiaen() {
		return cortesiaen;
	}

	public void setCortesiaen(String cortesiaen) {
		this.cortesiaen = cortesiaen;
	}

	public String getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(String cotizaciones) {
		this.cotizaciones = cotizaciones;
	}

	public String getReservas() {
		return reservas;
	}

	public void setReservas(String reservas) {
		this.reservas = reservas;
	}

	public String getFreserva() {
		return freserva;
	}

	public void setFreserva(String freserva) {
		this.freserva = freserva;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getDreservas() {
		return dreservas;
	}

	public void setDreservas(String dreservas) {
		this.dreservas = dreservas;
	}

	public String getNombrempleado() {
		return nombrempleado;
	}

	public void setNombrempleado(String nombrempleado) {
		this.nombrempleado = nombrempleado;
	}

	public String getFechadigitacion() {
		return fechadigitacion;
	}

	public void setFechadigitacion(String fechadigitacion) {
		this.fechadigitacion = fechadigitacion;
	}

//----- get y set Pagos

	public int getPago1() {
		return pago1;
	}

	public void setPago1(int pago1) {
		this.pago1 = pago1;
	}

	public String getFecha1() {
		return fecha1;
	}

	public void setFecha1(String fecha1) {
		this.fecha1 = fecha1;
	}

	public int getPago2() {
		return pago2;
	}

	public void setPago2(int pago2) {
		this.pago2 = pago2;
	}

	public String getFecha2() {
		return fecha2;
	}

	public void setFecha2(String fecha2) {
		this.fecha2 = fecha2;
	}

	public int getPago3() {
		return pago3;
	}

	public void setPago3(int pago3) {
		this.pago3 = pago3;
	}

	public String getFecha3() {
		return fecha3;
	}

	public void setFecha3(String fecha3) {
		this.fecha3 = fecha3;
	}

	public int getPago4() {
		return pago4;
	}

	public void setPago4(int pago4) {
		this.pago4 = pago4;
	}

	public String getFecha4() {
		return fecha4;
	}

	public void setFecha4(String fecha4) {
		this.fecha4 = fecha4;
	}

	public int getPago5() {
		return pago5;
	}

	public void setPago5(int pago5) {
		this.pago5 = pago5;
	}

	public String getFecha5() {
		return fecha5;
	}

	public void setFecha5(String fecha5) {
		this.fecha5 = fecha5;
	}

	public int getPago6() {
		return pago6;
	}

	public void setPago6(int pago6) {
		this.pago6 = pago6;
	}

	public String getFecha6() {
		return fecha6;
	}

	public void setFecha6(String fecha6) {
		this.fecha6 = fecha6;
	}

	public int getPago7() {
		return pago7;
	}

	public void setPago7(int pago7) {
		this.pago7 = pago7;
	}

	public String getFecha7() {
		return fecha7;
	}

	public void setFecha7(String fecha7) {
		this.fecha7 = fecha7;
	}

	public int getPago8() {
		return pago8;
	}

	public void setPago8(int pago8) {
		this.pago8 = pago8;
	}

	public String getFecha8() {
		return fecha8;
	}

	public void setFecha8(String fecha8) {
		this.fecha8 = fecha8;
	}

	public int getPago9() {
		return pago9;
	}

	public void setPago9(int pago9) {
		this.pago9 = pago9;
	}

	public String getFecha9() {
		return fecha9;
	}

	public void setFecha9(String fecha9) {
		this.fecha9 = fecha9;
	}

	public int getPago10() {
		return pago10;
	}

	public void setPago10(int pago10) {
		this.pago10 = pago10;
	}

	public String getFecha10() {
		return fecha10;
	}

	public void setFecha10(String fecha10) {
		this.fecha10 = fecha10;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", contrato=" + contrato + ", procedencia=" + procedencia + ", name=" + name
				+ ", ncelular=" + ncelular + ", nfijo=" + nfijo + ", email=" + email + ", nacionalidad=" + nacionalidad
				+ ", edad=" + edad + ", tipo=" + tipo + ", fnac=" + fnac + ", fini=" + fini + ", ffin=" + ffin
				+ ", cuantia=" + cuantia + ", cuotaunica=" + cuotaunica + ", valorcontrato=" + valorcontrato
				+ ", valordia=" + valordia + ", saldo=" + saldo + ", cortesia=" + cortesia + ", dcortesia=" + dcortesia
				+ ", cortesiaen=" + cortesiaen + ", cotizaciones=" + cotizaciones + ", reservas=" + reservas
				+ ", dreservas=" + dreservas + ", freserva=" + freserva + ", nombrempleado=" + nombrempleado
				+ ", fechadigitacion=" + fechadigitacion + ", pago1=" + pago1 + ", fecha1=" + fecha1 + ", pago2="
				+ pago2 + ", fecha2=" + fecha2 + ", pago3=" + pago3 + ", fecha3=" + fecha3 + ", pago4=" + pago4
				+ ", fecha4=" + fecha4 + ", pago5=" + pago5 + ", fecha5=" + fecha5 + ", pago6=" + pago6 + ", fecha6="
				+ fecha6 + ", pago7=" + pago7 + ", fecha7=" + fecha7 + ", pago8=" + pago8 + ", fecha8=" + fecha8
				+ ", pago9=" + pago9 + ", fecha9=" + fecha9 + ", pago10=" + pago10 + ", fecha10=" + fecha10 + "]";
	}
}