package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private Double kwota;
	private Double rata;
	private Double oprocentowanie;
	private Double result;

	@Inject
	FacesContext ctx;


	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	public Double getRata() {
		return rata;
	}

	public void setRata(Double rata) {
		this.rata = rata;
	}

	public Double getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(Double oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public Double getResult() {
		return result;
	}

	public boolean doTheMath() {
		try {

			result = (getKwota()*(1+(getOprocentowanie()/100)))/getRata();

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}
}
