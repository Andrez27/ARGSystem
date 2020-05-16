package py.edu.facitec.arg_system.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class FechaUtil {

	private final static SimpleDateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");
	private static DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	private static MaskFormatter mascara;
	private static MaskFormatter formatter;


	public static MaskFormatter getMascara() {
		if (mascara == null) {
			try {
				mascara = new MaskFormatter("##/##/####");
				mascara.setPlaceholderCharacter('_');
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return mascara;
	}
	
	public static MaskFormatter getFormatoHora(){
		try {
			formatter = new MaskFormatter("##:##");
			formatter.setPlaceholderCharacter(' ');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formatter;
	}

	public static Date convertirStringADate(String s) {
		FORMATO.setLenient(false);// no acepta fechas fuera de rangos validos
		try {
			return FORMATO.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date convertirStringFechaHora(String s) {
		hourdateFormat.setLenient(false);// no acepta fechas fuera de rangos validos
		try {
			return hourdateFormat.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String convertirDateAString(Date d) {
		if (d == null) {
			return null;
		} else {
			return FORMATO.format(d);
		}
	}
	
	public static Date stringAHora(String text){
		HOUR_FORMAT.setLenient(false);
		try {
			return HOUR_FORMAT.parse(text);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String convertirHoraAString(Date fecha){
		return HOUR_FORMAT.format(fecha);
	}

}
