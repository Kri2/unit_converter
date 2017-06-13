package pl.traza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.traza.domain.UnitConverter;

@Repository
public class UnitsConverterDAO {
	@Autowired
	private DataSource dataSource;
	
	private Float temperatureInCelsius = 0.0F;
	public void setTemperatureInCelsius(Float temperatureInCelsius){
		this.temperatureInCelsius=temperatureInCelsius;
	}
	public void setTemperatureInCelsius(Float temperatureInCelsius, String str){//później zmienic na unitConverter
		String sql = "INSERT INTO unit_converter (temperature_C, temperature_F) VALUES (?, ?)";
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, temperatureInCelsius);
			ps.setFloat(2, 0.0F);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){}
			}
		}
	}
	
	public Float getTemperatureInCelsius(){
		return this.temperatureInCelsius;
	}
	public Float getTemperatureInCelsius(Integer id){
		String sql = "SELECT * FROM unit_converter WHERE id=?";
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); //będzie podstawiał id do 1 miejsca z ? w sql string
			UnitConverter unitConverter = null;
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				unitConverter = new UnitConverter();
				unitConverter.setTemperatureC( rs.getFloat("temperature_C") );
			}
			rs.close();
			ps.close();
			return unitConverter.getTemperatureC();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			if(conn!=null){
				try{
					conn.close();
				}catch(SQLException e){}
			}
		}
	}
}
