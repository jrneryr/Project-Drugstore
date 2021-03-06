package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {

	public void salvar(Produto p) throws SQLException {
		
		StringBuilder sql= new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, quantidade, preco, fabricante_codigo) ");
		sql.append("VALUES(?, ?, ?, ?) ");
		
		Connection con= ConexaoFactory.conectar();
		PreparedStatement stmt= con.prepareStatement(sql.toString());
		stmt.setString(1, p.getDescricao());
		stmt.setLong(2,p.getQuantidade());
		stmt.setDouble(3, p.getPreco());
		
		stmt.setLong(4, p.getFabricante().getCodigo());
		
		stmt.executeUpdate();
		con.close();
	}

	public ArrayList<Produto> listar()throws SQLException{
		
		StringBuilder sql= new StringBuilder();
		sql.append("select p.codigo, p.descricao, p.quantidade, p.preco, f.codigo, f.descricao ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f on f.codigo = p.fabricante_codigo ");
		
		Connection con= ConexaoFactory.conectar();
		PreparedStatement stmt= con.prepareStatement(sql.toString());
		
		ResultSet rs= stmt.executeQuery();
		ArrayList<Produto> itens= new ArrayList<Produto>();
		while(rs.next()){
			Fabricante f= new Fabricante();
			f.setCodigo(rs.getLong("f.codigo"));
			f.setDescricao(rs.getString("f.descricao"));
			
			Produto p= new Produto();
			p.setCodigo(rs.getLong("p.codigo"));
			p.setDescricao(rs.getString("p.descricao"));
			p.setQuantidade(rs.getLong("p.quantidade"));
			p.setPreco(rs.getDouble("p.preco"));
			p.setFabricante(f);
			
			itens.add(p);
		}
		return itens;
	}
	
	public void escluir(Produto p) throws SQLException {
		
		StringBuilder sql= new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE codigo=? ");
		
		Connection con= ConexaoFactory.conectar();
		PreparedStatement stmt= con.prepareStatement(sql.toString());
		stmt.setLong(1, p.getCodigo());
		
		stmt.executeUpdate();
	}
	
	public void editar(Produto p) throws SQLException {
		
		StringBuilder sql= new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao=?, quantidade=?, preco=?, fabricante_codigo=? ");
		sql.append("WHERE codigo=?");
		
		Connection con= ConexaoFactory.conectar();
		PreparedStatement stmt= con.prepareStatement(sql.toString());
		stmt.setString(1, p.getDescricao());
		stmt.setLong(2, p.getQuantidade());
		stmt.setDouble(3, p.getPreco());
		stmt.setLong(4, p.getFabricante().getCodigo());
		stmt.setLong(5, p.getCodigo());
		
		stmt.executeUpdate();
	}
}
