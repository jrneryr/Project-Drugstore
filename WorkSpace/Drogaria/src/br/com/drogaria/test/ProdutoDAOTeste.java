package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class ProdutoDAOTeste {
	@Test
	@Ignore
	public void salvar() throws SQLException{
	Produto p= new Produto();
	p.setDescricao("teste Junit salvar");
	p.setPreco(10.00);
	p.setQuantidade(10L);
	
	Fabricante f= new Fabricante();
	f.setCodigo(15L); //codigo do fabricante n�o precisa settar a descri��o
	
	p.setFabricante(f);
	
	ProdutoDAO dao= new ProdutoDAO();
	
	dao.salvar(p);
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException {
		
		ProdutoDAO dao= new ProdutoDAO();
		ArrayList<Produto> lista= lista= dao.listar();
	}
	@Test
	@Ignore
	public void excluir() throws SQLException{
		
		Produto p= new Produto();
		ProdutoDAO dao= new ProdutoDAO();
		p.setCodigo(2L);
		
		dao.escluir(p);
	}
	@Test
	public void editar() throws SQLException {
		Produto p= new Produto();
		p.setCodigo(9L);
		p.setDescricao("teste editar");
		p.setQuantidade(10L);
		p.setPreco(10.10);
		
		Fabricante f= new Fabricante();
		f.setCodigo(31L);
		p.setFabricante(f);
		
		ProdutoDAO dao= new ProdutoDAO();
		dao.editar(p);
	}
}
