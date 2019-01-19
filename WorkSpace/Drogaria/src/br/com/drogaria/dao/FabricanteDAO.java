package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {

	public void salvar(Fabricante f) throws SQLException{
		StringBuilder sql= new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection con= ConexaoFactory.conectar();

		PreparedStatement stmt= con.prepareStatement(sql.toString());
		stmt.setString(1, f.getDescricao());

		stmt.executeUpdate();
	    ConexaoFactory.closeConnection(con, stmt);
	}

	public void excluir(Fabricante f) throws SQLException{
		StringBuilder sql= new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo=? ");

		Connection con= ConexaoFactory.conectar();

		PreparedStatement stmt= con.prepareStatement(sql.toString());
		stmt.setLong(1, f.getCodigo());

		stmt.executeUpdate();
		ConexaoFactory.closeConnection(con, stmt);
	}

	public void editar(Fabricante f)throws SQLException{
		StringBuilder sql= new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao=? ");
		sql.append("WHERE codigo=? ");

		Connection con= ConexaoFactory.conectar();

		PreparedStatement stmt= con.prepareStatement(sql.toString());
		stmt.setString(1, f.getDescricao());
		stmt.setLong(2, f.getCodigo());

		stmt.executeUpdate();
		ConexaoFactory.closeConnection(con, stmt);
	}

	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException{
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT codigo,descricao FROM fabricante ");
		sql.append("WHERE codigo=? ");

		Connection con= ConexaoFactory.conectar();
		PreparedStatement stmt= con.prepareStatement(sql.toString());
		stmt.setLong(1, f.getCodigo());

		ResultSet rs= stmt.executeQuery();

		Fabricante retorno= null;
		if(rs.next()){
			retorno= new Fabricante();
			retorno.setCodigo(rs.getLong("codigo"));
			retorno.setDescricao(rs.getString("descricao"));
		}
		ConexaoFactory.closeConnection(con, stmt, rs);
		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException{
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT* FROM ");
		sql.append("fabricante ");
		sql.append("ORDER BY descricao ASC ");

		Connection con= ConexaoFactory.conectar();
		PreparedStatement stmt= con.prepareStatement(sql.toString());
		ResultSet rs= stmt.executeQuery();

		ArrayList<Fabricante> lista= new ArrayList<Fabricante>();

		while(rs.next()){
			Fabricante f= new Fabricante();
			f.setCodigo(rs.getLong("codigo"));
			f.setDescricao(rs.getString("descricao"));

			lista.add(f);
		}
		ConexaoFactory.closeConnection(con, stmt, rs);
		return lista;
	}

	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException {
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT* FROM ");
		sql.append("fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection con= ConexaoFactory.conectar();
		PreparedStatement stmt= con.prepareStatement(sql.toString());
		stmt.setString(1, "%"+ f.getDescricao() +"%");

ResultSet rs= stmt.executeQuery();

		ArrayList<Fabricante> lista= new ArrayList<Fabricante>();

		while(rs.next()){
			Fabricante fab= new Fabricante();
			fab.setCodigo(rs.getLong("codigo"));
			fab.setDescricao(rs.getString("descricao"));

			lista.add(fab);
		}
		ConexaoFactory.closeConnection(con, stmt, rs);
		return lista;
	}

	public int controlFab(Fabricante fabricante){
		if(fabricante ==null){
			return 0;
		}else {
			return 1;
		}
	}

}
