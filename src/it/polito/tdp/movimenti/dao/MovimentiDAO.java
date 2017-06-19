package it.polito.tdp.movimenti.dao;

import it.polito.tdp.movimenti.bean.Circoscrizione;
import it.polito.tdp.movimenti.bean.Movimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MovimentiDAO {
	
	public List<Movimento> getAllMovimenti() {
		String sql = "SELECT * FROM movimenti_intraurbani";

		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);

			List<Movimento> movimenti = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {

				Calendar cal = Calendar.getInstance() ;
				cal.clear();
				cal.set(
						res.getInt("ANNO_REGISTRAZIONE"),
						res.getInt("MESE_REGISTRAZIONE")-1,
						res.getInt("GIORNO_REGISTRAZIONE")
						) ;
				
				
				Movimento mov = new Movimento(
						cal.getTime(),
						res.getInt("CIRCOSCRIZIONE_PROVENIENZA"),
						res.getInt("CIRCOSCRIZIONE_DESTINAZIONE"),
						res.getInt("TOTALE_EVENTI")
						);
				movimenti.add(mov);

			}

			st.close();
			conn.close();

			return movimenti;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error", e);
		}

	}

	public List<Circoscrizione> getCircoscrizioni() {
		
		String sql = "select distinct CIRCOSCRIZIONE_PROVENIENZA AS prov\r\n" + 
				"FROM movimenti_intraurbani as m\r\n" + 
				"ORDER BY prov";

		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			List<Circoscrizione> lista = new ArrayList<>();
			ResultSet rs = st.executeQuery();

			while(rs.next()) {

				Circoscrizione c=new Circoscrizione(rs.getInt("prov"));
				lista.add(c);
			}
			st.close();
			conn.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error", e);
		}	
	}

	public int getNumeroMovimenti(Circoscrizione partenza, Circoscrizione arrivo) {
		String sql = "select sum(TOTALE_EVENTI) as movimenti\r\n" + 
				"FROM movimenti_intraurbani as m\r\n" + 
				"where CIRCOSCRIZIONE_PROVENIENZA=? and CIRCOSCRIZIONE_DESTINAZIONE=?\r\n" + 
				"group by CIRCOSCRIZIONE_PROVENIENZA,CIRCOSCRIZIONE_DESTINAZIONE";

		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			int res = 0;
			st.setInt(1, partenza.getNum());
			st.setInt(2, arrivo.getNum());
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				res=rs.getInt("movimenti");
			}
			st.close();
			conn.close();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error", e);
		}
	}
}
